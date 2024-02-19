package com.smartClinic.smartclinic.entidades;

import com.smartClinic.smartclinic.DTO.EnderecoRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_endereco")
public class Endereco {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String bairro;
    @NotBlank(message = "cidade é obrigatório!")
    private String cidade;
    private String numero;
    private String uf;
    @NotBlank(message = "CEP é obrigatório!")
    private String cep;
    private String complemento;
    
    
    
    
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public Endereco(Long id, String rua, String bairro, String cidade, String numero, String uf, String cep,
			String complemento) {
		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.uf = uf;
		this.cep = cep;
		this.complemento = complemento;
	}
	
	public Endereco() {
	}
	
	public Endereco(Endereco endereco) {
		this.id = endereco.getId();
		this.rua = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.numero = endereco.getNumero();
		this.uf = endereco.getUf();
		this.cep = endereco.getCep();
		this.complemento = endereco.getComplemento();
	}
	
	
	public Endereco(EnderecoRequestDTO enderecoDTO) {
		this.rua = enderecoDTO.rua();
		this.bairro = enderecoDTO.bairro();
		this.cidade = enderecoDTO.cidade();
		this.numero = enderecoDTO.numero();
		this.uf = enderecoDTO.uf();
		this.cep = enderecoDTO.cep();
		this.complemento = enderecoDTO.complemento();
	}
	
}