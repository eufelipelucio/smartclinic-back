package com.smartClinic.smartclinic.DTO;

import java.time.LocalDate;

import com.smartClinic.smartclinic.entidades.Endereco;

public record PacienteRequestDTO(String nome, String sobrenome, String cpf, LocalDate dataNascimento, String telefone, String celular,
		String email, String senha, Endereco endereco) {
}
