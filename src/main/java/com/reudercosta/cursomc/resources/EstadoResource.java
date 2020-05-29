package com.reudercosta.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reudercosta.cursomc.DTO.EstadoDTO;
import com.reudercosta.cursomc.DTO.cidadeDTO;
import com.reudercosta.cursomc.domain.Cidade;
import com.reudercosta.cursomc.domain.Estado;
import com.reudercosta.cursomc.services.CidadeService;
import com.reudercosta.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {

		List<Estado> list = service.findAll();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@RequestMapping(value="/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<cidadeDTO>> findCidades(@PathVariable Integer estadoId) {

		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<cidadeDTO> listDTO = list.stream().map(obj -> new cidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}
}
