package com.my.app.face_catch;

 
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.my.app.face_catch.detectors.CascadeDetector;
import com.my.app.face_catch.detectors.FaceDetector;
import com.my.app.face_catch.processors.VisualSearchAlgorithm;

@ComponentScan
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        Property property = (Property) context.getBean("property");
        FaceDetector faces = (FaceDetector) context.getBean("cascade");

		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    		

    	    Mat src = Imgcodecs.imread( property.getProperty("opencv.input") );
    	
    	    // Instantiating the CascadeClassifier
//    	    CascadeClassifier classifier = new CascadeClassifier( property.getProperty("opencv.cascade") );
//    	
//    	    // Detecting the face in the snap
//    	    MatOfRect faceDetections = new MatOfRect();
//    	    classifier.detectMultiScale(src, faceDetections);
//    	    System.out.println(String.format("Detected %s inst", faceDetections.toArray().length));
    	
    	    // Drawing boxes
//    	    for (Rect rect : faceDetections.toArray()) {
    	    for (Rect rect : faces.find(src)) {
    	    	
    	    	Point startPoint = new Point(rect.x, rect.y);
    	    	Point endPoint = new Point(rect.x + rect.width, rect.y + rect.height);
    	    	
    	
    	    	Imgproc.rectangle(
    	            src,                                              
    	            new Point(rect.x, rect.y + rect.height),                          
    	            new Point(rect.x + rect.width, rect.y + rect.height + 40),
    	            new Scalar(11, 11, 11),
    	            Imgproc.FILLED                                // Thickness
    	         );
    	//    	Imgproc.rectangle(
    	//		  src,                                              
    	//		  startPoint,                          
    	//		  endPoint,
    	//		  new Scalar(11, 11, 11),
    	//		 1                              // Thickness
    	//		);
    	//    	
    	//        // Adding Text
    	//        Imgproc.putText (
    	//        	src,
    	//           " Unknown " + i,
    	//           new Point(rect.x, rect.y + rect.height + 12),
    	//           Imgproc.FONT_HERSHEY_SIMPLEX ,
    	//           0.5,                               // front scale
    	//           new Scalar(222, 222, 222),
    	//           1                                // Thickness
    	//       );
    	    // Create rectangle with face
    	    	Mat faceMats = src.submat( new Rect(startPoint, endPoint) );
    	    }
    	    
    	    // Writing the image
    	    Imgcodecs.imwrite( property.getProperty("opencv.output"), src );
    	    
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