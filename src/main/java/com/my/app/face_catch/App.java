package com.my.app.face_catch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
 *
 */

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class App 
{
    public static void main( String[] args )
    {
    	final String PROPERTY_FILE = "src/main/resources/settings.properties";
    	
    	final Properties PROPERTY = new Properties();
        try {
        	PROPERTY.load(new FileInputStream( PROPERTY_FILE ));
        } catch (IOException e) {
        	e.printStackTrace();
        }
    	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    	
    	 // Reading the Image from the file and storing it in to a Matrix object
        Mat src = Imgcodecs.imread( PROPERTY.getProperty("opencv.input") );

        // Instantiating the CascadeClassifier
        CascadeClassifier classifier = new CascadeClassifier( PROPERTY.getProperty("opencv.cascade") );

        // Detecting the face in the snap
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(src, faceDetections);
        System.out.println(String.format("Detected %s inst", faceDetections.toArray().length));

        
//        List<Mat> allFaceMats = new ArrayList<Mat>();
        int i = 0;
        // Drawing boxes
        for (Rect rect : faceDetections.toArray()) {
        	Point startPoint = new Point(rect.x, rect.y);
        	Point endPoint = new Point(rect.x + rect.width, rect.y + rect.height);
        	

//        	Imgproc.rectangle(
//                src,                                              
//                new Point(rect.x, rect.y + rect.height),                          
//                new Point(rect.x + rect.width, rect.y + rect.height + 40),
//                new Scalar(11, 11, 11),
//                Imgproc.FILLED                                // Thickness
//             );
//        	Imgproc.rectangle(
//			  src,                                              
//			  startPoint,                          
//			  endPoint,
//			  new Scalar(11, 11, 11),
//			 1                              // Thickness
//			);
//        	
//            // Adding Text
//            Imgproc.putText (
//            	src,
//               " Unknown " + i,
//               new Point(rect.x, rect.y + rect.height + 12),
//               Imgproc.FONT_HERSHEY_SIMPLEX ,
//               0.5,                               // front scale
//               new Scalar(222, 222, 222),
//               1                                // Thickness
//           );
        // Create rectangle with face
        	Mat faceMats = src.submat( new Rect(startPoint, endPoint) );
        	
        // Writing the image
           String output = PROPERTY.getProperty("opencv.output") + "_" + i + ".jpeg";
           Imgcodecs.imwrite( output, faceMats );
           i++;
//           allFaceMats.add(faceMats);
        }
        
        // Writing the image
//        Imgcodecs.imwrite( PROPERTY.getProperty("opencv.output"), src );

        System.out.println("Image Processed");
    }
}
//--------------- GET PIXEL SAMPLE
//double[] rgb = image.get(0, 0); Mat - image
//Log.i("", "red:"+rgb[0]+"green:"+rgb[1]+"blue:"+rgb[2]);
//image.put(0, 0, new double[]{255, 255, 0});//sets the pixel to yellow
//--------------- LOAD MAT FROM BYTES SAMPLE
//byte[] bytes = FileUtils.readFileToByteArray(new File("aaa.jpg"));
//Mat mat = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);