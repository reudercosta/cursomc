package com.reudercosta.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.reudercosta.cursomc.DTO.CategoriaDTO;
import com.reudercosta.cursomc.DTO.ClienteDTO;
import com.reudercosta.cursomc.domain.Categoria;
import com.reudercosta.cursomc.domain.Cliente;
import com.reudercosta.cursomc.domain.Cliente;
import com.reudercosta.cursomc.repositories.ClienteRepository;
import com.reudercosta.cursomc.services.excpetions.DataIntegrityException;
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
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir existem entidade relacionadas");
		}

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
	}
	
	private void updateData(Cliente newObj, Cliente Obj) {
		newObj.setNome(Obj.getNome());
		newObj.setEmail(Obj.getEmail());
	}

}
