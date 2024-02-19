package com.smartClinic.smartclinic.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.smartClinic.smartclinic.Exceptions.CpfDuplicadoException;
import com.smartClinic.smartclinic.Exceptions.EmailDuplicadoException;
import com.smartClinic.smartclinic.Exceptions.NotFoundException;
import com.smartClinic.smartclinic.entidades.Administrador;
import com.smartClinic.smartclinic.repositories.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	AdministradorRepository administradorRepository;

	@Autowired
	UserService userService;
	
	public Administrador salvarAdministrador(Administrador administrador) {
		if(userService.loadUserByUsername(administrador.getEmail()) != null){
			throw new EmailDuplicadoException("Usuário já cadastrado!");
		};
		if(administradorRepository.existsByCpf(administrador.getCpf())){
			throw new CpfDuplicadoException("CPF já cadastrado!");
		};
		
		Random geraCodigo = new Random();
		Integer codigo = geraCodigo.nextInt(5000);
		administrador.setCodigo_admin("adm-" + codigo.toString());
		return administradorRepository.save(administrador);
	}
	
	public List<Administrador> consultar(){
		return administradorRepository.findAll();
	}
	
	public Administrador consultarPorId(Long administradorId) {
		return administradorRepository.findById(administradorId)
				.orElseThrow(()-> new NotFoundException("Administrador não encontrado para o ID: " + administradorId));
	}
	
	public Administrador atualizarAdministrador(Long id, Administrador novoAdministrador) {
		Administrador administradorExistente = administradorRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Administrador não encontrado"));

				String novoEmail = novoAdministrador.getEmail();
		String novoCpf = novoAdministrador.getCpf();
		
		
		if(!administradorExistente.getEmail().equalsIgnoreCase(novoEmail)){
			UserDetails user =	administradorRepository.findByEmail(novoEmail);
			if(user != null){
				throw new EmailDuplicadoException("Email já cadastrado");
			}
		}
		
		if(!administradorExistente.getCpf().equalsIgnoreCase(novoCpf)){
			if(administradorRepository.existsByCpf(novoCpf)){
				throw new CpfDuplicadoException("CPF já cadastrado!");
			}
		}

		
		administradorExistente.setNome(novoAdministrador.getNome());
		administradorExistente.setSobrenome(novoAdministrador.getSobrenome());
		administradorExistente.setCpf(novoAdministrador.getCpf());
		administradorExistente.setCelular(novoAdministrador.getCelular());
		administradorExistente.setDataNascimento(novoAdministrador.getDataNascimento());
		administradorExistente.setTelefone(novoAdministrador.getTelefone());
		administradorExistente.setEmail(novoAdministrador.getEmail());
		administradorExistente.setEndereco(novoAdministrador.getEndereco());

		return administradorRepository.save(administradorExistente);
	}
	
	public Administrador excluir(Long id) {
		Administrador administradorParaExcluir = administradorRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Administrador não encontrado"));
		administradorRepository.deleteById(id);
		return administradorParaExcluir;
	}
}
