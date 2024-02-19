package com.smartClinic.smartclinic.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartClinic.smartclinic.DTO.PacienteRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_paciente")
public class Paciente extends Usuario {
	
    @Column(unique = true)
    @NotBlank(message = "código do paciente é obrigatório!")
	private String codigoPaciente;
	
    @JsonBackReference
	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consultas = new ArrayList<>();
	
	public String getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(String codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	public Paciente(Long id, String nome, String sobrenome, String cpf, String telefone, String celular, String email,
			String senha, Endereco endereco, String codigo_paciente, List<Consulta> consultas) {
		super(id, nome, sobrenome, cpf, telefone, celular, email, senha, endereco);
		this.codigoPaciente = codigo_paciente;
		this.consultas = consultas;
	}

	public Paciente() {
	}
	
	public Paciente(PacienteRequestDTO pacienteDTO) {
		this.setNome(pacienteDTO.nome());
		this.setSobrenome(pacienteDTO.sobrenome());
		this.setCpf(pacienteDTO.cpf());
		this.setDataNascimento(pacienteDTO.dataNascimento());
		this.setTelefone(pacienteDTO.telefone());
		this.setCelular(pacienteDTO.celular());
		this.setEmail(pacienteDTO.email());
		this.setSenha(pacienteDTO.senha());
		this.setEndereco(pacienteDTO.endereco());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));
	}
}