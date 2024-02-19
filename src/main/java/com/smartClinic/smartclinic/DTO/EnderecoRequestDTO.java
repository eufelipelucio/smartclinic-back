package com.smartClinic.smartclinic.DTO;

public record EnderecoRequestDTO(String rua, String bairro, String cidade, String numero, String uf, String cep,
		String complemento) {
}
