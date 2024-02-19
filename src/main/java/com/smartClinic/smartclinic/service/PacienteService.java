package com.smartClinic.smartclinic.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.smartClinic.smartclinic.Exceptions.CpfDuplicadoException;
import com.smartClinic.smartclinic.Exceptions.EmailDuplicadoException;
import com.smartClinic.smartclinic.Exceptions.NotFoundException;
import com.smartClinic.smartclinic.entidades.Paciente;
import com.smartClinic.smartclinic.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	UserService userService;

	public Paciente salvarPaciente(Paciente paciente) {
		if(userService.loadUserByUsername(paciente.getEmail()) != null){
			throw new EmailDuplicadoException("Email já cadastrado!");
		};
		if(pacienteRepository.existsByCpf(paciente.getCpf())){
			throw new CpfDuplicadoException("CPF já cadastrado!");
		};
		Random geraCodigo = new Random();
		Integer codigo = geraCodigo.nextInt(5000);
		paciente.setCodigoPaciente("cod-" + codigo.toString());
		return pacienteRepository.save(paciente);
	}

	public List<Paciente> consultar() {
		return pacienteRepository.findAll();
	}

	public Paciente consultarPorId(Long pacienteId) {
		return pacienteRepository.findById(pacienteId).orElseThrow(()-> new NotFoundException("Paciente não encontrado"));
	}

	public Paciente atualizarPaciente(Long id, Paciente novoPaciente) {
		Paciente pacienteExistente = pacienteRepository.findById(id).orElseThrow(()-> new NotFoundException("Paciente não encontrado"));
		String novoEmail = novoPaciente.getEmail();
		String novoCpf = novoPaciente.getCpf();
		
		
		if(!pacienteExistente.getEmail().equalsIgnoreCase(novoEmail)){
			UserDetails user =	pacienteRepository.findByEmail(novoEmail);
			if(user != null){
				throw new EmailDuplicadoException("Email já cadastrado");
			}
		}
		
		if(!pacienteExistente.getCpf().equalsIgnoreCase(novoCpf)){
			if(pacienteRepository.existsByCpf(novoCpf)){
				throw new CpfDuplicadoException("CPF já cadastrado!");
			}
		}

		pacienteExistente.setNome(novoPaciente.getNome());
		pacienteExistente.setSobrenome(novoPaciente.getSobrenome());
		pacienteExistente.setCpf(novoPaciente.getCpf());
		pacienteExistente.setCelular(novoPaciente.getCelular());
		pacienteExistente.setTelefone(novoPaciente.getTelefone());
		pacienteExistente.setEmail(novoPaciente.getEmail());
		pacienteExistente.setDataNascimento(novoPaciente.getDataNascimento());
		pacienteExistente.setEndereco(novoPaciente.getEndereco());
		
		return pacienteRepository.save(pacienteExistente);
	}

	public Paciente excluir(Long id) {
		Paciente pacienteParaExcluir = pacienteRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Paciente não encontrado"));
		pacienteRepository.deleteById(id);
		return pacienteParaExcluir;
	}

	public Paciente consultarPorCodigoPaciente(String codigo_paciente){
		Paciente paciente = pacienteRepository.findByCodigoPaciente(codigo_paciente)
			.orElseThrow(()-> new NotFoundException("Paciente não encontrado"));
		return paciente;
	}
}