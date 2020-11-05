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
import com.my.app.face_catch.services.visual_algorithms.SearchResult;
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
			List<SearchResult> foundFaces = builder.getVsa().find(image);
			List<Personnel> imagesFromDataBase = builder.getPersonnelDao().getPersonnel();
			
			//CALCULATE DIFFERENCES BETWEEN FOUND FACE IMAGE MATRICES WITH DB MATRICES
			Map<SearchResult, Optional<Personnel>> comparisonResult = matchPersonnelWithFoundFaces(foundFaces, imagesFromDataBase);
			
			export(comparisonResult);
		}
	}
	
	private Map<SearchResult, Optional<Personnel>> matchPersonnelWithFoundFaces(List<SearchResult> foundFaces, List<Personnel> imagesFromDataBase){
		// CONVERT IMAGE BYTE ARR FROM DB TO OPENCV MATRIX
		Map<Mat, Personnel> personnelWithConvertedImages = imagesFromDataBase
				.stream()
				.collect(
						Collectors.toMap(
								p -> convertByteToMat(p.getImage()), 
								p -> p
								)
						);
		
		return foundFaces
				.stream()
				.collect(
						Collectors.toMap(
									sr-> sr,
									sr-> Optional.ofNullable(
											findSituablePersonnel(personnelWithConvertedImages, sr)
											)
									)
						);
	}
	private Personnel findSituablePersonnel(Map<Mat, Personnel> personnelWithConvertedImages, SearchResult searchResult) {
		Set<Mat> situableMatrix = personnelWithConvertedImages.keySet()
									.stream()
									.filter(m -> builder.getVca().compare(m, searchResult.getMat()) < 9.0)
									.collect(Collectors.toSet());
		if (situableMatrix.isEmpty()) {
			return null;
		}
		return personnelWithConvertedImages.get( 
						situableMatrix.iterator().next() 
						);
	}
	
	
	private Mat convertByteToMat(byte bytes[]) {
		return Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);
	}
	
	private void export(Map<SearchResult, Optional<Personnel>> comparisonResult) {
		
		RectWithInfo rectWithInfo = new RectWithInfo();
		
		comparisonResult.forEach((searchResult, personnel) -> {
			
			Rect rect = searchResult.getRect();
			
			if (personnel.isPresent()) {
				rectWithInfo.drawValidRect(rect, image, personnel.get());
			} else {
				rectWithInfo.drawUnknownRect(rect, image);
			}
		});
		
		Imgcodecs.imwrite(builder.getExportPath() , image);
	}
}
