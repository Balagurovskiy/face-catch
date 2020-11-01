package com.my.app.face_catch.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.my.app.face_catch.db.dto.Personnel;

public class PersonnelDao {

	private Configuration configuration;
	private boolean active;

	SessionFactory sessionFactory;

	public PersonnelDao(String cfgFile) {
		active = true;
		try {
			configuration = new Configuration().configure(cfgFile);
		} catch (HibernateException e) {
			System.out.println("HIBERNATE EXCEPTION - INVALID CONFIGURATION FILE");
			active = false;
		}
	}

	public void init() {
		if (active) {
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}

	public List<Personnel> getPersonnel() {

		List<Personnel> list = new ArrayList<>();
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (NullPointerException e) {
			System.out.println("PERSONEL DAO EXCEPTION - SESSION FACTORY WAS NOT SETTED");
			return list;
		}

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Personnel");
			list = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return list;
	}

	public void destroy() {

		if (active) {
			sessionFactory.close();

		}
	}
}
