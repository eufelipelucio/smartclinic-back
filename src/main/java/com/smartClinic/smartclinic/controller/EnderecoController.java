package com.smartClinic.smartclinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartClinic.smartclinic.DTO.EnderecoRequestDTO;
import com.smartClinic.smartclinic.DTO.EnderecoResponseDTO;
import com.smartClinic.smartclinic.entidades.Endereco;
import com.smartClinic.smartclinic.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvarEndereco(@RequestBody @Valid EnderecoRequestDTO enderecoDTO){
		Endereco enderecoSalvo = new Endereco(enderecoDTO);
		enderecoService.salvarEndereco(enderecoSalvo);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new EnderecoResponseDTO("Endereço adicionado com Sucesso!"));
	}
	
	@GetMapping
	public ResponseEntity<List<Endereco>> buscarEndereco(){
		List<Endereco> enderecos = enderecoService.consultar();
		
		if(!enderecos.isEmpty()) {
			return ResponseEntity.ok(enderecos);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> consultarPorId(@PathVariable Long id){
		Endereco endereco = enderecoService.consultarPorId(id);
		return ResponseEntity.ok(endereco);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarEndereco(@PathVariable Long id,
			@RequestBody @Valid EnderecoRequestDTO enderecoDTO){
		Endereco enderecoAtualizado = new Endereco(enderecoDTO);
		enderecoService.atualizarEndereco(id, enderecoAtualizado);
		return ResponseEntity.ok().body(new EnderecoResponseDTO("Endereço atualizado com Sucesso!"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Endereco> excluirEndereco(@PathVariable Long id){
		enderecoService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}