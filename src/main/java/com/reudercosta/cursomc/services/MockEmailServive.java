package com.reudercosta.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailServive extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailServive.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando Envio de Email...");
		LOG.info(msg.toString());
		LOG.info("Email Enviado!!");
		
	}
	

}
