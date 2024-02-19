package com.smartClinic.smartclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartClinic.smartclinic.Exceptions.NotFoundException;
import com.smartClinic.smartclinic.entidades.Endereco;
import com.smartClinic.smartclinic.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco salvarEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public List<Endereco> consultar(){
		return enderecoRepository.findAll();
	}
	
	public Endereco consultarPorId(Long enderecoId) {
		return enderecoRepository.findById(enderecoId)
				.orElseThrow(()-> new NotFoundException("Endereço não encontrado"));
	}
	
	public Endereco atualizarEndereco(Long id, Endereco novoEndereco) {
		Endereco enderecoExistente = enderecoRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Endereço não encontrado"));
		
		enderecoExistente.setRua(novoEndereco.getRua());
		enderecoExistente.setBairro(novoEndereco.getBairro());
		enderecoExistente.setCidade(novoEndereco.getCidade());
		enderecoExistente.setNumero(novoEndereco.getNumero());
		enderecoExistente.setUf(novoEndereco.getUf());
		enderecoExistente.setCep(novoEndereco.getCep());
		enderecoExistente.setComplemento(novoEndereco.getComplemento());
		
		return enderecoRepository.save(enderecoExistente);
	}
	
	public Endereco excluir(Long id) {
		Endereco enderecoParaExcluir = enderecoRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("Endereço não encontrado"));
		enderecoRepository.deleteById(id);
		return enderecoParaExcluir;
	}
}