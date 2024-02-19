package com.smartClinic.smartclinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartClinic.smartclinic.DTO.ProfissionalRequestDTO;
import com.smartClinic.smartclinic.DTO.ProfissionalResponseDTO;
import com.smartClinic.smartclinic.entidades.Profissional;
import com.smartClinic.smartclinic.service.ProfissionalService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/profissionais")
public class ProfissionalController {
	
	@Autowired
	private ProfissionalService profissionalService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvarProfissional(@RequestBody @Valid ProfissionalRequestDTO profissionalDTO){
		String encryptedPassword = new BCryptPasswordEncoder().encode(profissionalDTO.senha());
		Profissional profissionalSalvo = new Profissional(profissionalDTO);

		profissionalSalvo.setSenha(encryptedPassword);
		profissionalService.salvarProfissional(profissionalSalvo);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ProfissionalResponseDTO("Profissional adicionado com Sucesso!"));
	}
	
	@GetMapping
	public ResponseEntity<List<Profissional>> buscarProfissionais(){
		List<Profissional> profissionais = profissionalService.consultar();
		
		if(!profissionais.isEmpty()) {
			return ResponseEntity.ok().body(profissionais);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Profissional> consultarPorId(@PathVariable Long id){
		Profissional profissional = profissionalService.consultarPorId(id);
		return ResponseEntity.ok().body(profissional);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarProfissional(@PathVariable Long id,
			@RequestBody @Valid ProfissionalRequestDTO profissionalDTO){
		Profissional profissionalAtualizado = new Profissional(profissionalDTO);
		profissionalService.atualizarProfissional(id, profissionalAtualizado);
		return ResponseEntity.ok().body(new ProfissionalResponseDTO("Profissional atualizado com Sucesso!"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Profissional> deletarProfissional(@PathVariable Long id){
		profissionalService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("especialidades/{especialidades}")
	public ResponseEntity<?> buscarProfissionaisPorEspecialidades(@PathVariable String especialidades) {
		List<Profissional> profissionais = profissionalService.buscarProfissionaisPorEspecialidades(especialidades);
		return ResponseEntity.ok().body(profissionais);
	}

	@GetMapping("/crmProfissional/{crmProfissional}")
	public ResponseEntity<Profissional> consultarProfissionalPorCrm(@PathVariable String crmProfissional){
		Profissional profissional = profissionalService.consultarProfissionalPorCrm(crmProfissional);
		return ResponseEntity.ok().body(profissional);
	}
}