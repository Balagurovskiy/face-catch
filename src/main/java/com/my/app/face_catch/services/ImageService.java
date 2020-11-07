package com.my.app.face_catch.services;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import com.my.app.face_catch.Property;
import com.my.app.face_catch.db.dto.Personnel;
import com.my.app.face_catch.drawing_utils.patterns.RectWithInfo;
import com.my.app.face_catch.services.builder.VusialServiceBuilder;
import com.my.app.face_catch.services.visual_algorithms.Image;
/**
* Service for operations with images based on input image.
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class ImageService implements VisualService {
	
	private Mat image;
	private boolean active;
	
	private VusialServiceBuilder builder;
	
	public ImageService() {
		active = false;
	}
    /**
     * Constructor. Inits opencv core. Create matrix from input image.
     * @param builder - contains import/export path, visual compare and detection algorithms, personnel from db
     * @return nothing
     */
	public ImageService(VusialServiceBuilder builder) {
		this.builder = builder;
		
		if (builder.isReady()) {
			active = true;
		} else {
			System.out.println("IMAGE PROCESSOR WAS NOT SETTED - VISUAL SERVICE BILDER FAILED");
	    	active = false;
	    	return ;
		}
	    try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
			image = Imgcodecs.imread( builder.getImportPath() );
	    }catch (UnsatisfiedLinkError e) {
	    	System.out.println("IMAGE PROCESSOR WAS NOT SETTED - OPEN CV CORE NOT INITIALIZED");
	    	active = false;
		}catch (NullPointerException e) {
			System.out.println("IMAGE PROCESSOR WAS NOT SETTED - THERE IS NO IMAGE SOURCE");
			active = false;
		}
	}
    /**
     * Creates matrices from detected areas. Loads personnel from DB. Compares detected matrices with images from DB. Draw boxes with result and save into new image.
     * @param nothing
     * @return nothing
     */
	@Override
	public void execute() {
		if (active) {
			List<Image> foundFaces = builder.getVisualSearchAlgorithm().find(image);
			
			List<Image> dataSource = builder.getVisualDataSource().create();
			
			//CALCULATE DIFFERENCES BETWEEN FOUND FACE IMAGE MATRICES WITH DB MATRICES
			List<Image> comparisonResult = matchDataSourceWithDetectedResults(foundFaces, dataSource);
			export(comparisonResult);
		}
	}
	
	private List<Image> matchDataSourceWithDetectedResults(List<Image> detected, List<Image> dataSource){
		return detected
				.stream()
				.map(detect -> {
					Optional<Image> os = Optional.ofNullable(findSituableResult(dataSource, detect));
					if (os.isPresent()) {
						detect.setInfo(os.get().getInfo());
					}
					return detect; 
					})
				.collect(Collectors.toList());
	}

	private Image findSituableResult(List<Image> resultWithInfo, Image resultToCompare) {
		Set<Image> situableMatrix = resultWithInfo
									.stream()
									.filter(sr -> builder.getVisualCompareAlgorithm().compare(sr.getMat(), resultToCompare.getMat()) < 9.0)
									.collect(Collectors.toSet());
		if (situableMatrix.isEmpty()) {
			return null;
		}
		return situableMatrix.iterator().next();
	}
	
	private void export(List<Image> comparisonResult) {
		
		RectWithInfo rectWithInfo = new RectWithInfo();
		
		comparisonResult.forEach((searchResult) -> {
			
			Rect rect = searchResult.getRect();
			
			if (searchResult.getInfo().isEmpty()) {
				rectWithInfo.drawUnknownRect(rect, image);
			} else {
				rectWithInfo.drawValidRect(rect, image, searchResult.getInfo());
			}
		});
		
		Imgcodecs.imwrite(builder.getExportPath() , image);
	}
}
