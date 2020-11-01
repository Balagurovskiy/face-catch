package com.my.app.face_catch.db.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonnelDaoConfiguration {

	private final String HIBERNATE_CFG = "face_catch.cfg.xml";
	
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public PersonnelDao personnelDao() {
		return new PersonnelDao( HIBERNATE_CFG );
	}
}
