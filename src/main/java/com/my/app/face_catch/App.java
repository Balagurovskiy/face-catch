package com.my.app.face_catch;

import static org.opencv.imgproc.Imgproc.INTER_AREA;
import static org.opencv.imgproc.Imgproc.resize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.my.app.face_catch.db.dao.PersonnelDao;
import com.my.app.face_catch.db.dao.TestTransaction;
import com.my.app.face_catch.db.dto.Personnel;
import com.my.app.face_catch.detectors.face.CascadeDetector;
import com.my.app.face_catch.detectors.face.FaceDetector;
import com.my.app.face_catch.services.VisualService;
import com.my.app.face_catch.services.search_algorithm.VisualSearchAlgorithm;
import com.my.app.face_catch.services.SearchResult;

import drawing_utils.DrawingUtils;
import drawing_utils.RectangleUtil;

@ComponentScan
public class App {
	
	public static DrawingUtils rectangleUtil;
	
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class)) {

			Property property = (Property) context.getBean("property");
			VisualService visualService = (VisualService) context.getBean("imageServiceCascade");
			PersonnelDao personnelDao = (PersonnelDao) context.getBean("personnelDao");

			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

			rectangleUtil = new RectangleUtil();
			
			//FIND FACES ON INPUT IMAGE
			List<SearchResult> foundImages = visualService.executeSearch();
			// DB SELECT
			List<Personnel> imagesFromDataBase = personnelDao.getPersonnel();
			// CONVERT IMAGE BYTE ARR FROM DB TO OPENCV MATRIX
			Map<Mat, Personnel> personnelWithConvertedImages = imagesFromDataBase
																.stream()
																.collect(
																		Collectors.toMap(
																				p -> convertByteToMat(p.getImage()), 
																				p -> p
																				)
																		);
			
			//CALCULATE DIFFERENCES BETWEEN FOUND FACE IMAGE MATRICES WITH DB MATRICES
			Map<SearchResult, Optional<Personnel>> comparisonResult = foundImages
															.stream()
															.collect(
																	Collectors.toMap(
																				sr-> sr,
																				sr-> Optional.ofNullable(
																						findSituablePersonnel(personnelWithConvertedImages, sr)
																						)
																				)
																	);
			//DRAW COMPARE RESULT ON INPUT MATRIX
			comparisonResult.forEach((searchResult, personnel) -> {
				
					Rect rect = searchResult.getRect();
					
					if (personnel.isPresent()) {
						drawValidRect(rect, visualService.getImage(), personnel.get());
					} else {
						
						drawUnknownRect(rect, visualService.getImage());
					}
				}
			);
			//EXPORT MATRIX TO IMAGE FILE AFTER DRAW
			visualService.export(property.getProperty("opencv.output"));

		}
	}
	static public final Scalar GREEN =  new Scalar(22, 111, 11);
	static public final Scalar RED =  new Scalar(11, 11, 222);
	
	static void drawValidRect(Rect rect, Mat source, Personnel personnel) {
		Point start = new Point(rect.x, rect.y + rect.height);
		Point end = new Point(rect.x + rect.width, rect.y + rect.height + 40);
		
		rectangleUtil.draw(source, rect, GREEN);
		rectangleUtil.drawFilled(source, start, end, GREEN);
		
		rectangleUtil.drawTextWhite(source,
				new Point(rect.x, rect.y + rect.height + 15),
				personnel.getName());
		
		rectangleUtil.drawTextWhite(source,
				new Point(rect.x, rect.y + rect.height + 30),
				personnel.getProfile().getRole());
	}
	
	static void drawUnknownRect(Rect rect, Mat source) {
		Point start = new Point(rect.x, rect.y + rect.height);
		Point end = new Point(rect.x + rect.width, rect.y + rect.height + 20);
		
		rectangleUtil.draw(source, rect, RED);
		rectangleUtil.drawFilled(source, start, end, RED);
		
    	rectangleUtil.drawTextWhite(source,  
    			new Point(rect.x, rect.y + rect.height + 12), 
    			"Unknown");
	}
	
	
//	Collector.of(
//	        ArrayList::new,				// supplier
//	        ArrayList::add,				// accumulator
//	        (a, b) -> {					// combiner
//	            a.addAll(b);
//	            return a;
//	        },
//	        a -> a.isEmpty() ? null : a  // finisher
//	    )
	static Personnel findSituablePersonnel(Map<Mat, Personnel> personnelWithConvertedImages, SearchResult searchResult) {
		Set<Mat> situableMatrix = personnelWithConvertedImages.keySet()
									.stream()
									.filter(m -> calcMatDiff(m, searchResult.getMat()) < 9.0)
									.collect(Collectors.toSet());
		if (situableMatrix.isEmpty()) {
			return null;
		}
		return personnelWithConvertedImages.get( 
						situableMatrix.iterator().next() 
						);
	}
	
	
	static Mat convertByteToMat(byte bytes[]) {
		return Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);
	}

	
	
	static double calcMatDiff(final Mat a, final Mat b) {

		final int HEIGHT = 100;
		final int WIDTH = 100;

		Size scaleSize = new Size(HEIGHT, WIDTH);

		Mat resA = new Mat();
		resize(a, resA, scaleSize, 0, 0, INTER_AREA);

		Mat resB = new Mat();
		resize(b, resB, scaleSize, 0, 0, INTER_AREA);

		long diff = 0;
		// split on threads
		for (int w = 0; w < WIDTH; w++) {
			for (int h = 0; h < HEIGHT; h++) {
				double[] rgbA = resA.get(w, h);
				double[] rgbB = resB.get(w, h);
				diff += Math.abs(rgbA[0] - rgbB[0]) + Math.abs(rgbA[1] - rgbB[1]) + Math.abs(rgbA[2] - rgbB[2]);
			}
		}
		double avg = diff / (WIDTH * HEIGHT * 3);

		return (avg / 255) * 100;
	}
}