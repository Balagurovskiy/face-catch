package com.my.app.face_catch.detectors.face;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import com.my.app.face_catch.Property;
import com.my.app.face_catch.services.visual_algorithms.SearchResult;

public class CaffeDetector implements FaceDetector {
	
    
//    private ToIplImage converter;
//    private Net net;
    
	public CaffeDetector(String protoFilePath, String caffeModelPath) {
//		net = readNetFromCaffe(protoFilePath, caffeModelPath);
//		converter = new OpenCVFrameConverter.ToIplImage();
	}
 
	@Override
	public List<SearchResult> find(Mat mat) {
//        resize(mat, mat, new Size(300, 300));//resize the image to match the input size of the model
//
//        //create a 4-dimensional blob from image with NCHW (Number of images in the batch -for training only-, Channel, Height, Width) dimensions order,
//        //for more detailes read the official docs at https://docs.opencv.org/trunk/d6/d0f/group__dnn.html#gabd0e76da3c6ad15c08b01ef21ad55dd8
//        Mat blob = blobFromImage(mat, 1.0, new Size(300, 300), new Scalar(104.0, 177.0, 123.0, 0), false, false, CV_32F);
//
//        net.setInput(blob);//set the input to network model
//        Mat output = net.forward();//feed forward the input to the netwrok to get the output matrix
//
//        Mat ne = new Mat(new Size(output.size(3), output.size(2)), CV_32F, output.ptr(0, 0));//extract a 2d matrix for 4d output matrix with form of (number of detections x 7)
//
//        FloatIndexer srcIndexer = ne.createIndexer(); // create indexer to access elements of the matrix

		return null;
	}

}
