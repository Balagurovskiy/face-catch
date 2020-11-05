package com.my.app.face_catch.drawing_utils.patterns;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

import com.my.app.face_catch.db.dto.Personnel;
import com.my.app.face_catch.drawing_utils.RectangleUtil;
/**
* Pattern for information box drawing 
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class RectWithInfo {
	RectangleUtil rectangleUtil;
	
	static public final Scalar GREEN =  new Scalar(22, 111, 11);
	static public final Scalar RED =  new Scalar(11, 11, 222);
    /**
     * Constructor initialize rectangle util
     * @param nothing
     * @return nothing
     */
	public RectWithInfo() {
		rectangleUtil = new RectangleUtil();
	}
    /**
     * Creates green rectangle with info box
     * @param rect - size for rectangle
     * @param source - base matrix
     * @param personnel - object with information (name / role)
     * @return nothing
     */
	public void drawValidRect(Rect rect, Mat source, Personnel personnel) {
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
    /**
     * Creates red rectangle with 'Unknown' box
     * @param rect - size for rectangle
     * @param source - base matrix
     * @return nothing
     */
	public void drawUnknownRect(Rect rect, Mat source) {
		Point start = new Point(rect.x, rect.y + rect.height);
		Point end = new Point(rect.x + rect.width, rect.y + rect.height + 20);
		
		rectangleUtil.draw(source, rect, RED);
		rectangleUtil.drawFilled(source, start, end, RED);
		
    	rectangleUtil.drawTextWhite(source,  
    			new Point(rect.x, rect.y + rect.height + 12), 
    			"Unknown");
	}
}
