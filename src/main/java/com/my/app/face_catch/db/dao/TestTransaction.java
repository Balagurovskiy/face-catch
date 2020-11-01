package com.my.app.face_catch.db.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.my.app.face_catch.db.dto.Personnel;

public class TestTransaction {
	public TestTransaction() {
		final String HIBERNATE_CFG = "face_catch_images.cfg.xml";

		Configuration configuration = null;
		try {
			configuration = new Configuration().configure(HIBERNATE_CFG);
		} catch (HibernateException e) {
			System.out.println("HIBERNATE EXCEPTION - INVALID CONFIGURATION FILE");
		}
		if (configuration == null) {
			return;
		}
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		File file = new File("test_resources/photo_sample/Kim Lee.jpeg");
		byte[] imageData = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(imageData);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Personnel image = new Personnel();
		image.setName("Kim Lee");
		image.setImage(imageData);

		session.save(image);

//		Personnel imgNew = (Personnel)session.get(Personnel.class, 1);
//        byte[] bAvatar = imgNew.getImage();
// 
//        try{
//            FileOutputStream fos = new FileOutputStream("test_resources/AdamSmithfromDB.jpeg");
//            fos.write(bAvatar);
//            fos.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }

		session.getTransaction().commit();

		sessionFactory.close();
	}
}
