package com.my.app.face_catch.db.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import com.my.app.face_catch.db.dto.Personnel;
import com.my.app.face_catch.services.visual_algorithms.Image;
import com.my.app.face_catch.services.visual_algorithms.VisualDataSource;
/**
* Adapter to convert personnel from DB to intstance that is more sitiable for visual services
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class PersonnelAdapter implements VisualDataSource {
	
	private List<Personnel> listWithPersonnel;
	
    /**
     * Constructor 
     * @param listWithPersonnel collection with personnel
     * @return nothing
     */
	public PersonnelAdapter(List<Personnel> listWithPersonnel) {
		this.listWithPersonnel = listWithPersonnel;
	}
	
	private Mat convertByteToMat(byte bytes[]) {
		return Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);
	}
	
    /**
     * Converts personnel BLOB to opencv Mat, copies name and role of personnel to Image info parameter 
     * @return collection with image matrices and info s  
     */
	@Override
	public List<Image> create() {
		return listWithPersonnel
				.stream()
				.map(personnel -> {
					Image sr = new Image(convertByteToMat(personnel.getImage()), new Rect());
					sr.addInfo(personnel.getName());
					sr.addInfo(personnel.getProfile().getRole());
					return sr;
				})
				.collect(Collectors.toList());
	}

}
