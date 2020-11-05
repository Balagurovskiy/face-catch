package com.my.app.face_catch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.my.app.face_catch.db.dao.PersonnelDao;
import com.my.app.face_catch.db.dto.Personnel;
import com.my.app.face_catch.db.dto.Profile;
import com.my.app.face_catch.services.VisualService;
/**
* The face-catch program implements an application that
* simply recognize and compares faces.
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
@ComponentScan
public class App {
	
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class)) {
			
			VisualService visualService = (VisualService) context.getBean("imageServiceCascade");
			visualService.execute();
		}
	}
}
