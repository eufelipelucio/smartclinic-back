package com.smartClinic.smartclinic.DTO;

import java.time.LocalDate;

import com.smartClinic.smartclinic.entidades.Endereco;

public record PerfilResponseDTO(String nome, Long id, String sobrenome, String cpf, LocalDate dataNascimento, String telefone, String celular,
		String email, Endereco endereco) {

}