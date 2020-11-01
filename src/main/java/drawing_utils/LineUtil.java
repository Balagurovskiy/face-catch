package drawing_utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class LineUtil implements DrawingUtils{

	
	private final int FILLED = 3;
	private final int BORDERED = 1;
	
	private int thikness;
	
	public LineUtil() {
		thikness = BORDERED;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}
	
	@Override
	public void draw(Mat src, Rect target, Scalar color) {
		thikness = BORDERED;
		
		Imgproc.line(
			src,                                              
			new Point(target.x, target.y),
			new Point(target.width + target.x, target.height + target.y),
			color,
			thikness
		);
	}

	@Override
	public void draw(Mat src, Point start, int dist, Scalar color) {
		thikness = BORDERED;
		Imgproc.line(
			src,                                              
			start,
			new Point(dist + start.x, dist + start.y),
			color,
			thikness
		);
	}

	@Override
	public void draw(Mat src, Point start, Point end, Scalar color) {
		thikness = BORDERED;
		
		Imgproc.line(
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
	}

	@Override
	public void drawFilled(Mat src, Point start, int dist, Scalar color) {
		thikness = FILLED;
		draw(src, start, dist, color);
	}

	@Override
	public void drawFilled(Mat src, Point start, Point end, Scalar color) {
		thikness = FILLED;
		draw(src, start, end, color);
	}
}
