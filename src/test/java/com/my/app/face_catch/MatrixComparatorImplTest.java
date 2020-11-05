package com.my.app.face_catch;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import com.my.app.face_catch.comparator.MatrixComparatorImpl;

public class MatrixComparatorImplTest {

	private static Mat matrixA;
	private static Mat matrixAA;
	private static Mat matrixB;
	
	
	
	@BeforeClass
	public static void initTest() {
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
		matrixA = new Mat(10, 10, CvType.CV_64FC3, new Scalar(1,1,1));
		matrixAA = new Mat(11, 11, CvType.CV_64FC3, new Scalar(1,1,1));
		matrixB = new Mat(10, 10, CvType.CV_64FC3, new Scalar(1,1,1));

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				matrixA.put(i, j, new double[]{3, 3, 3});
			}
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				matrixAA.put(i, j, new double[]{3, 3, 3});
			}
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				matrixB.put(i, j, new double[]{111, 111, 111});
			}
		}
	}
	
	@Test
	public void matricesAreEqual() {
		MatrixComparatorImpl matrixComparatorImpl = new MatrixComparatorImpl(10, 10);
		assertTrue("Compare same matrix",matrixComparatorImpl.compare(matrixA, matrixA) < 0.5);
	}
	
	
	@Test
	public void matricesHaveToBeEqual() {
		MatrixComparatorImpl matrixComparatorImpl = new MatrixComparatorImpl(10, 10);
		assertTrue("Compare matrices, with same content and different size ",matrixComparatorImpl.compare(matrixA, matrixAA) < 0.5);
	}
	
	@Test
	public void matricesAreNotEqual() {
		MatrixComparatorImpl matrixComparatorImpl = new MatrixComparatorImpl(10, 10);
		assertTrue("Compare different matrices",matrixComparatorImpl.compare(matrixA, matrixB) > 30);
	}
}
