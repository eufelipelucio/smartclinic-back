package com.smartClinic.smartclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartClinic.smartclinic.Exceptions.NotFoundException;
import com.smartClinic.smartclinic.entidades.Consulta;
import com.smartClinic.smartclinic.entidades.Paciente;
import com.smartClinic.smartclinic.entidades.Profissional;
import com.smartClinic.smartclinic.repositories.ConsultaRepository;
import com.smartClinic.smartclinic.repositories.PacienteRepository;
import com.smartClinic.smartclinic.repositories.ProfissionalRepository;


@Service
public class ConsultaService {
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;

	@Autowired
	ProfissionalRepository profissionalRepository;


	public Consulta salvarConsulta(Consulta consulta) {
		Optional<Paciente> paciente = pacienteRepository.findById(consulta.getPaciente().getId());
		Optional<Profissional> profissional = profissionalRepository.findById(consulta.getProfissional().getId());
		consulta.setPaciente(paciente.get());
		consulta.setProfissional(profissional.get());
		double protocolo = Math.random() * 1000;
		consulta.setProtocolo(String.valueOf(protocolo));
		return consultaRepository.save(consulta);
	}
	
	public List<Consulta> consultar(){
		return consultaRepository.findAll();
	}
	
	public Consulta consultarPorId(Long consultaId) {
		return consultaRepository.findById(consultaId)
				.orElseThrow(()-> new NotFoundException("Consulta não encontrada"));
	}
	
	public Consulta atualizarConsulta(Long id, Consulta novaConsulta) {
		Consulta consultaExistente = consultaRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Consulta não encontrada"));
		
		consultaExistente.setData(novaConsulta.getData());
		consultaExistente.setHora(novaConsulta.getHora());
		consultaExistente.setObservacao(novaConsulta.getObservacao());
		consultaExistente.setObservacao_prontuario(novaConsulta.getObservacao_prontuario());
		consultaExistente.setPaciente(novaConsulta.getPaciente());
		consultaExistente.setProfissional(novaConsulta.getProfissional());
		consultaExistente.setProtocolo(novaConsulta.getProtocolo());
		consultaExistente.setStatus_consulta(novaConsulta.getStatus_consulta());
		
		return consultaRepository.save(consultaExistente);
	}
	
	public Consulta excluir(Long id) {
		Consulta consultaParaExcluir = consultaRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Consulta não encontrada"));
		consultaRepository.deleteById(id);
		return consultaParaExcluir;
	}

	public List<Consulta> listarConsultasPacientes(Paciente paciente){
		return consultaRepository.findByPaciente(paciente);
	}

	public List<Consulta> listarConsultasProfissionais(Profissional profissional){
		return consultaRepository.findByProfissional(profissional);
	}
}