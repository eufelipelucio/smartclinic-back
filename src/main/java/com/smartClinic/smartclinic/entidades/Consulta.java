package com.smartClinic.smartclinic.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartClinic.smartclinic.DTO.ConsultaRequestDTO;
import com.smartClinic.smartclinic.enums.StatusConsulta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_consulta")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_consulta;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora;

	private String protocolo;
	
	@Enumerated(EnumType.STRING)
	private StatusConsulta status_consulta;
	private String observacao;
	private String observacao_prontuario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paciente_id",referencedColumnName = "id")
    private Paciente paciente;
    
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profissional_id",referencedColumnName = "id")
	private Profissional profissional;
    
	
	public Long getId_consulta() {
		return id_consulta;
	}
	public void setId_consulta(Long id_consulta) {
		this.id_consulta = id_consulta;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public StatusConsulta getStatus_consulta() {
		return status_consulta;
	}
	public void setStatus_consulta(StatusConsulta status_consulta) {
		this.status_consulta = status_consulta;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getObservacao_prontuario() {
		return observacao_prontuario;
	}
	public void setObservacao_prontuario(String observacao_prontuario) {
		this.observacao_prontuario = observacao_prontuario;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public Consulta(Long id_consulta, LocalDate data, LocalTime hora, String protocolo,
			StatusConsulta status_consulta, String observacao, String observacao_prontuario, Paciente paciente,
			 Profissional profissional) {
		this.id_consulta = id_consulta;
		this.data = data;
		this.hora = hora;
		this.protocolo = protocolo;
		this.status_consulta = status_consulta;
		this.observacao = observacao;
		this.observacao_prontuario = observacao_prontuario;
		this.paciente = paciente;
		this.profissional = profissional;
	}
	
	public Consulta() {
	}
    public Consulta(ConsultaRequestDTO consulta) {
        this.data = consulta.data();
		this.hora = consulta.hora();
		this.protocolo = consulta.protocolo();
		this.status_consulta = consulta.status_consulta();
		this.observacao = consulta.observacao();
		this.observacao_prontuario = consulta.observacao_prontuario();
		this.paciente = consulta.paciente();
		this.profissional = consulta.profissional();
    }
	
}