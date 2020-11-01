package drawing_utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class RectangleUtil implements DrawingUtils {

	
	private final int FILLED = Imgproc.FILLED;
	private final int BORDERED = 2;
	
	private int thikness;
	
	public RectangleUtil() {
		thikness = BORDERED;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}
	
	@Override
	public void draw(Mat src, Rect target, Scalar color) {
		Imgproc.rectangle(
			src,                                              
			target,
			color,
			thikness
		);
	}

	@Override
	public void draw(Mat src, Point start, int dist, Scalar color) {
		Imgproc.rectangle(
			src,                                              
			start,
			new Point(start.x + dist, start.y + dist),
			color,
			thikness
		);
	}

	@Override
	public void draw(Mat src, Point start, Point end, Scalar color) {
		Imgproc.rectangle(
			src,                                              
			start,
			end,
			color,
			thikness
		);
	}

	@Override
	public void drawFilled(Mat src, Rect target, Scalar color) {
		thikness = FILLED;
		draw(src, target, color);
		thikness = BORDERED;
	}

	@Override
	public void drawFilled(Mat src, Point start, int dist, Scalar color) {
		thikness = FILLED;
		draw(src, start, dist, color);
		thikness = BORDERED;
	}

	@Override
	public void drawFilled(Mat src, Point start, Point end, Scalar color) {
		thikness = FILLED;
		draw(src, start, end, color);
		thikness = BORDERED;
	}
 
}
