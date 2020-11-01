package com.my.app.face_catch;

import static org.opencv.imgproc.Imgproc.INTER_AREA;
import static org.opencv.imgproc.Imgproc.resize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.my.app.face_catch.detectors.CascadeDetector;
import com.my.app.face_catch.detectors.FaceDetector;
import com.my.app.face_catch.services.VisualService;
import com.my.app.face_catch.services.SearchResult;
import com.my.app.face_catch.services.VisualSearchAlgorithm;

import drawing_utils.DrawingUtils;
import drawing_utils.RectangleUtil;

@ComponentScan
public class App {
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class)) {

			Property property = (Property) context.getBean("property");
			VisualService visualService = (VisualService) context.getBean("imageServiceCascade");
			PersonnelDao personnelDao = (PersonnelDao) context.getBean("personnelDao");

			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

			DrawingUtils rectangleUtil = new RectangleUtil();
			
			
			List<SearchResult> foundImages = visualService.executeSearch();

 
//    	    // Create rectangle with face
////	    	Mat faceMats = src.submat( new Rect(startPoint, endPoint) );

			Map<Mat, Personnel> imagesFromDataBase = personnelDao.getPersonnel()
													.stream()
													.collect(
															Collectors.toMap(
																	p -> convertByteToMat(p.getImage()), 
																	p -> p
																	)
															);

		    List<SearchResult> unknown = new ArrayList<>();
		    List<SearchResult> known = new ArrayList<>();
			
			imagesFromDataBase.forEach((keyMat, valPers) -> {
				foundImages.forEach(searchResult -> {
					Rect rect = searchResult.getRect();
					Point start = new Point(rect.x, rect.y + rect.height);
					Point end = new Point(rect.x + rect.width, rect.y + rect.height + 40);

					if (calcMatDiff(keyMat, searchResult.getMat()) < 1.0) {

						rectangleUtil.draw(visualService.getImage(), rect, new Scalar(22, 111, 11));
						rectangleUtil.drawFilled(visualService.getImage(), start, end, new Scalar(22, 111, 11));
						rectangleUtil.drawTextWhite(visualService.getImage(),
								new Point(rect.x, rect.y + rect.height + 15),
								valPers.getName());
						rectangleUtil.drawTextWhite(visualService.getImage(),
								new Point(rect.x, rect.y + rect.height + 30),
								valPers.getProfile().getRole());
						
						known.add(new SearchResult(keyMat, searchResult.getRect()));
					}
		    		else {
		    			rectangleUtil.draw(visualService.getImage(), rect, new Scalar(11, 11, 222));
		    			rectangleUtil.drawFilled(visualService.getImage(), start, end, new Scalar(11, 11, 222));
				    	rectangleUtil.drawTextWhite(visualService.getImage(),  
				    			new Point(rect.x, rect.y + rect.height + 12), 
				    			"Unknown");
		    		}
				});
			});
			
			visualService.export(property.getProperty("opencv.output"));

		}
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