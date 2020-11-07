package com.my.app.face_catch.db.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
* Personnel DAO configuration
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
@Configuration
public class PersonnelConfiguration {

	private final String HIBERNATE_CFG = "face_catch.cfg.xml";
	
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public PersonnelDao personnelDao() {
		return new PersonnelDao( HIBERNATE_CFG );
	}
	@Bean
	public PersonnelAdapter personnelAdapter(PersonnelDao personnelDao) {
		return new PersonnelAdapter( personnelDao.getPersonnel() );
	}
}
