package com.smartClinic.smartclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.smartClinic.smartclinic.Exceptions.CpfDuplicadoException;
import com.smartClinic.smartclinic.Exceptions.EmailDuplicadoException;
import com.smartClinic.smartclinic.Exceptions.NotFoundException;
import com.smartClinic.smartclinic.entidades.Profissional;
import com.smartClinic.smartclinic.repositories.ProfissionalRepository;

@Service
public class ProfissionalService {
	
	@Autowired
	ProfissionalRepository profissionalRepository;
	@Autowired
	UserService userService;

	public Profissional salvarProfissional(Profissional profissional) {
		if(userService.loadUserByUsername(profissional.getEmail()) != null){
			throw new EmailDuplicadoException("Usuário já cadastrado!");
		};
		if(profissionalRepository.existsByCpf(profissional.getCpf())){
			throw new CpfDuplicadoException("CPF já cadastrado!");
		};
		return profissionalRepository.save(profissional);
	}
	
	public List<Profissional> consultar(){
		return profissionalRepository.findAll();
	}
	
	public Profissional consultarPorId(Long profissionalId) {
		Profissional profissionalExistente = profissionalRepository.findById(profissionalId).orElseThrow();
		return profissionalExistente;
	}
	
	public Profissional atualizarProfissional(Long id, Profissional novoProfissional) {
		Profissional profissionalExistente = profissionalRepository.findById(id).orElseThrow(()-> new NotFoundException("Profissional não encontrado"));
		String novoEmail = novoProfissional.getEmail();
		String novoCpf = novoProfissional.getCpf();
		
		
		if(!profissionalExistente.getEmail().equalsIgnoreCase(novoEmail)){
			UserDetails user =	profissionalRepository.findByEmail(novoEmail);
			if(user != null){
				throw new EmailDuplicadoException("Email já cadastrado");
			}
		}
		
		if(!profissionalExistente.getCpf().equalsIgnoreCase(novoCpf)){
			if(profissionalRepository.existsByCpf(novoCpf)){
				throw new CpfDuplicadoException("CPF já cadastrado!");
			}
		}

		
		profissionalExistente.setNome(novoProfissional.getNome());
		profissionalExistente.setSobrenome(novoProfissional.getSobrenome());
		profissionalExistente.setCpf(novoProfissional.getCpf());
		profissionalExistente.setDataNascimento(novoProfissional.getDataNascimento());
		profissionalExistente.setCelular(novoProfissional.getCelular());
		profissionalExistente.setTelefone(novoProfissional.getTelefone());
		profissionalExistente.setEmail(novoProfissional.getEmail());
		profissionalExistente.setCrmProfissional(novoProfissional.getCrmProfissional());
		profissionalExistente.setEndereco(novoProfissional.getEndereco());
		profissionalExistente.setConsultas(novoProfissional.getConsultas());
		
		return profissionalRepository.save(profissionalExistente);
	}
	
	public Profissional excluir(Long id) {
		Profissional profissionalParaExcluir = profissionalRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Profissional não encontrado"));
		profissionalRepository.deleteById(id);
		return profissionalParaExcluir;
	}
	
	public List<Profissional> buscarProfissionaisPorEspecialidades(String especialidades) {
		List<Profissional> profissionais = profissionalRepository.findByEspecialidades(especialidades);
		if(profissionais.isEmpty()) {
			throw new NotFoundException("Os Profissionais com essa Especialidade não foram encontrados.");
		}
		return profissionalRepository.findByEspecialidades(especialidades);
	}

		public Profissional consultarProfissionalPorCrm(String crmProfissional){
		Profissional profissional = profissionalRepository.findByCrmProfissional(crmProfissional)
			.orElseThrow(()-> new NotFoundException("Profissional não encontrado"));
		return profissional;
	}
}