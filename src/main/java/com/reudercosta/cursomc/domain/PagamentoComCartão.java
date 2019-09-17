package com.reudercosta.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.reudercosta.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartão extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer numentoDeParcelas;

	public PagamentoComCartão() {
	}

	public PagamentoComCartão(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numentoDeParcelas=numeroDeParcelas;

	}

	public Integer getNumentoDeParcelas() {
		return numentoDeParcelas;
	}

	public void setNumentoDeParcelas(Integer numentoDeParcelas) {
		this.numentoDeParcelas = numentoDeParcelas;
	}
	
	
	
}
