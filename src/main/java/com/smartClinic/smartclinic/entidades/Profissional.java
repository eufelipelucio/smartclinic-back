package com.smartClinic.smartclinic.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartClinic.smartclinic.DTO.ProfissionalRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_profissional")
public class Profissional extends Usuario {

    @Column(unique = true)
    @NotBlank(message = "CRM é obrigatório!")
	private String crmProfissional;

	@Column
	@NotBlank(message = "Especialidade é obrigatório!")
	private String especialidades;
	
	@JsonBackReference
	@OneToMany(mappedBy = "profissional")
	private List<Consulta> consultas = new ArrayList<>();

	public String getCrmProfissional() {
		return crmProfissional;
	}
	
	public void setCrmProfissional(String crmProfissional) {
		this.crmProfissional = crmProfissional;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public String getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}

	public Profissional(Long id, String nome, String sobrenome, String cpf, String telefone, String celular,
			String email, String senha, Endereco endereco, String crmProfissional, List<Consulta> consultas, String especialidades) {
		super(id, nome, sobrenome, cpf, telefone, celular, email, senha, endereco);
		this.crmProfissional = crmProfissional;
		this.consultas = consultas;
		this.especialidades = especialidades;
	}

	public Profissional() {
	}
	
	public Profissional(ProfissionalRequestDTO profissionalDTO) {
		this.setNome(profissionalDTO.nome());
		this.setSobrenome(profissionalDTO.sobrenome());
		this.setCpf(profissionalDTO.cpf());
		this.setDataNascimento(profissionalDTO.dataNascimento());
		this.setTelefone(profissionalDTO.telefone());
		this.setCelular(profissionalDTO.celular());
		this.setEmail(profissionalDTO.email());
		this.setSenha(profissionalDTO.senha());
		this.setEndereco(profissionalDTO.endereco());
		this.setCrmProfissional(profissionalDTO.crmProfissional());
		this.setEspecialidades(profissionalDTO.especialidades());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_PROFISSIONAL"));
	}
}