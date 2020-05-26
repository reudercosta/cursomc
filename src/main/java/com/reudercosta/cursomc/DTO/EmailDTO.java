package com.reudercosta.cursomc.DTO;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento Obrigatório!!")
	@Email(message = "Email inválido!")
	private  String Email;
	
	public EmailDTO() {
		
	}
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}	
	
	

}
