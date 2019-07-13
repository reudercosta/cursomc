package com.reudercosta.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE (1,"pendente"),
	QUITADO(2,"quitado"),
	CANCELADO(3, "cancelado");
	
	private int cod;
	private String descrição;

	private EstadoPagamento(int cod, String descrição) {

		this.cod = cod;
		this.descrição = descrição;

	}

	public int getCod() {
		return cod;
	}

	public String getDescrição() {
		return descrição;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido" + cod);

	}

}
