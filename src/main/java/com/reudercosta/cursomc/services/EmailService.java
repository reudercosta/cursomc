package com.reudercosta.cursomc.services;


import org.springframework.mail.SimpleMailMessage;

import com.reudercosta.cursomc.domain.Pedido;


public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}

