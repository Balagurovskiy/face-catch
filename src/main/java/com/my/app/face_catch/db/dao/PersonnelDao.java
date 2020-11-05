package com.my.app.face_catch.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.my.app.face_catch.db.dto.Personnel;
import com.my.app.face_catch.db.dto.Profile;
/**
* DAO for personnel info
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class PersonnelDao {

	private Configuration configuration;
	private boolean active;

	SessionFactory sessionFactory;
    /**
     * Constructor
     * @param cfgFile -file with hibernate properties
     * @return nothing
     */
	public PersonnelDao(String cfgFile) {
		active = true;
		try {
			configuration = new Configuration().configure(cfgFile);
		} catch (HibernateException e) {
			System.out.println("HIBERNATE EXCEPTION - INVALID CONFIGURATION FILE");
			active = false;
		}
	}
    /**
     * Hibernate session factory initialization method
     * @param nothing
     * @return nothing
     */
	public void init() {
		if (active) {
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
    /**
     * Makes select to personnel entity
     * @param nothing
     * @return list with all personnel
     */
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
    /**
     * Insert new personnel to the DB
     * @param name
     * @param image
     * @param profile
     * @return id of newly inserted personnel or -1 on exception
     */
	public Integer savePersonnel(String name, byte[] image, Integer profileId) {
		Integer id = -1;
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (NullPointerException e) {
			System.out.println("PERSONEL DAO EXCEPTION - SESSION FACTORY WAS NOT SETTED");
			return id;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Personnel personnel = new Personnel();
			personnel.setName(name);
			personnel.setImage(image);
			personnel.setProfile( (Profile) session.get(Profile.class, profileId) );
			
			id = (Integer) session.save(personnel);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return id;
	}
    /**
     * Delete personnel from the DN by the id
     * @param id
     * @return true if transaction was successful otherwise false
     */
	public boolean deletePersonnel(Integer id) {
		boolean status = true;
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (NullPointerException e) {
			System.out.println("PERSONEL DAO EXCEPTION - SESSION FACTORY WAS NOT SETTED");
			status = false;
			return status;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Personnel personnel =  (Personnel) session.get(Personnel.class, id);
			session.delete(personnel);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				status = false;
			}
		} finally {
			session.close();
		}
		return status;
	}
    /**
     * Select personnel by id
     * @param id
     * @return Optional instance on personnel
     */
	public Optional<Personnel> getPersonnelById(Integer id) {
		Optional<Personnel> personnel = Optional.empty();
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (NullPointerException e) {
			System.out.println("PERSONEL DAO EXCEPTION - SESSION FACTORY WAS NOT SETTED");
			return personnel;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			personnel = Optional.ofNullable( (Personnel) session.get(Personnel.class, id) );
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return personnel;
	}
    /**
     * Hibernate session factory close method
     * @param nothing
     * @return nothing
     */
	public void destroy() {

		if (active) {
			sessionFactory.close();
		}
	}
}
