package com.my.app.face_catch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.my.app.face_catch.services.VisualService;

@ComponentScan
public class App {
	
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class)) {

			VisualService visualService = (VisualService) context.getBean("imageServiceCascade");
			visualService.execute();
		}
	}
}
