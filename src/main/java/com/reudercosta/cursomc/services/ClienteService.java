package com.reudercosta.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reudercosta.cursomc.domain.Cliente;
import com.reudercosta.cursomc.repositories.ClienteRepository;
import com.reudercosta.cursomc.services.excpetions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: "+ id
					+"Tipo: "+ Cliente.class.getName());
		}
		return obj;
	}

}
