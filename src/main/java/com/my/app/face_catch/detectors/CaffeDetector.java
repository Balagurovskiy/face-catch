package com.my.app.face_catch.detectors;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import com.my.app.face_catch.Property;

public class CaffeDetector implements FaceDetector {
	
    private static final String PROTO_FILE = "opencv.proto";
    private static final String CAFFE_MODEL_FILE = "opencv.caffe-model";
    
    private boolean status;
    
//    private ToIplImage converter;
//    private Net net;
    
	public CaffeDetector(Property property) {
		status = true;
//		net = readNetFromCaffe(PROTO_FILE, CAFFE_MODEL_FILE);
//		converter = new OpenCVFrameConverter.ToIplImage();
	}

	@Override
	public List<Rect> find(Mat mat) {
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
