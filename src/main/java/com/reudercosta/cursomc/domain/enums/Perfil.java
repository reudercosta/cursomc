package com.reudercosta.cursomc.domain.enums;

public enum Perfil {
	
	ADMIN (1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	private int cod;
	private String descrição;

	private Perfil(int cod, String descrição) {

		this.cod = cod;
		this.descrição = descrição;

	}

	public int getCod() {
		return cod;
	}

	public String getDescrição() {
		return descrição;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido" + cod);

	}

}
