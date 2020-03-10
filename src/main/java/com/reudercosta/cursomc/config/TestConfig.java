package com.reudercosta.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.reudercosta.cursomc.services.DBService;
import com.reudercosta.cursomc.services.EmailService;
import com.reudercosta.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig{
	@Autowired
	private DBService dbservice;

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		dbservice.instantiateTestDataBase();

		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
