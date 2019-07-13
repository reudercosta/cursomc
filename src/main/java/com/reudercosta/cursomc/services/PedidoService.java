package com.reudercosta.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reudercosta.cursomc.domain.Pedido;
import com.reudercosta.cursomc.repositories.PedidoRepository;
import com.reudercosta.cursomc.services.excpetions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: "+ id
					+"Tipo: "+ Pedido.class.getName());
		}
		return obj;
	}

}
