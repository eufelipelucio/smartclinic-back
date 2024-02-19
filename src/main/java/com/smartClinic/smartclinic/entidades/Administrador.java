package com.smartClinic.smartclinic.entidades;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.smartClinic.smartclinic.DTO.AdministradorRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_administrador")
public class Administrador extends Usuario{
	
    @Column(unique = true)
    @NotBlank(message = "código do administrador é obrigatório!")
	private String codigo_admin;

	public String getCodigo_admin() {
		return codigo_admin;
	}

	public void setCodigo_admin(String codigo_admin) {
		this.codigo_admin = codigo_admin;
	}

	public Administrador(Long id, String nome, String sobrenome, String cpf, String telefone, String celular,
			String email, String senha, Endereco endereco, String codigo_admin) {
		super(id, nome, sobrenome, cpf, telefone, celular, email, senha, endereco);
		this.codigo_admin = codigo_admin;
	}

	public Administrador() {
	}
	
	public Administrador(AdministradorRequestDTO administradorDTO) {
		this.setNome(administradorDTO.nome());
		this.setSobrenome(administradorDTO.sobrenome());
		this.setCpf(administradorDTO.cpf());
		this.setDataNascimento(administradorDTO.dataNascimento());
		this.setTelefone(administradorDTO.telefone());
		this.setCelular(administradorDTO.celular());
		this.setEmail(administradorDTO.email());
		this.setSenha(administradorDTO.senha());
		this.setEndereco(administradorDTO.endereco());
		this.setCodigo_admin(administradorDTO.codigo_admin());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
}