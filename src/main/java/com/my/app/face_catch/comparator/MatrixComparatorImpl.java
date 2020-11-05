package com.my.app.face_catch.comparator;

import static org.opencv.imgproc.Imgproc.INTER_AREA;
import static org.opencv.imgproc.Imgproc.resize;

import javax.validation.constraints.Min;

import org.opencv.core.Mat;
import org.opencv.core.Size;

import com.my.app.face_catch.Property;
/**
* Pixel comparator
* <p>
* Compares rgb difference for each pixel in input matrices
* </p>
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class MatrixComparatorImpl implements MatrixComparator {

	private final int DEFAULT_HEIGHT = 100;
	private final int DEFAULT_WIDTH = 100;
	
	private Mat tempA;
	private Mat tempB;
	
	//@Min(value = DEFAULT_HEIGHT, message = "Height should not be less than " + DEFAULT_HEIGHT)
	private int height;
	//@Min(value = DEFAULT_WIDTH, message = "Width should not be less than " + DEFAULT_WIDTH)
	private int width;
    /**
     * Constructor with comparison field size setter
     * @param height, width - size of comparison field (all input matrices will be resized by this height/width before compare)
     */
	public MatrixComparatorImpl(int height, int width) {
		this.height = (height < DEFAULT_HEIGHT) ? DEFAULT_HEIGHT : height;
		this.width = (width < DEFAULT_WIDTH) ? DEFAULT_WIDTH : width;
	}
	
	private double pixelDiff(double[] rgbA, double[] rgbB) {
		if (rgbA.length < 3 || rgbB.length < 3) {
			return -1;
		}
		return  Math.abs(rgbA[0] - rgbB[0]) + 
				Math.abs(rgbA[1] - rgbB[1]) + 
				Math.abs(rgbA[2] - rgbB[2]);
	}
	private double matDiff( Mat tempA,  Mat tempB) {
		double diff = 0;
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				double[] rgbA = tempA.get(w, h);
				double[] rgbB = tempB.get(w, h);
				diff += pixelDiff(rgbA, rgbB);
			}
		}
		return diff;
	}
    /**
     * Implementation of compare method
     * <p>
     * calculates the average difference of pixels by rgb values
     * </p>
     * @param a, b - matrices that will be compared
     * @return percent of difference
     */
	@Override
	public double compare(Mat a, Mat b) {

		Size scaleSize = new Size(height, width);

		tempA = new Mat();
		resize(a, tempA, scaleSize, 0, 0, INTER_AREA);

		tempB = new Mat();
		resize(b, tempB, scaleSize, 0, 0, INTER_AREA);

		double avg = matDiff(tempA, tempB) / (width * height * 3);

		return (avg / 255) * 100;
	}

}
