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

import com.smartClinic.smartclinic.DTO.PacienteRequestDTO;
import com.smartClinic.smartclinic.DTO.PacienteResponseDTO;
import com.smartClinic.smartclinic.entidades.Paciente;
import com.smartClinic.smartclinic.service.PacienteService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvarPaciente(@RequestBody @Valid PacienteRequestDTO pacienteDTO){
		String encryptedPassword = new BCryptPasswordEncoder().encode(pacienteDTO.senha());
		Paciente pacienteSalvo = new Paciente(pacienteDTO);

		pacienteSalvo.setSenha(encryptedPassword);
		pacienteService.salvarPaciente(pacienteSalvo);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new PacienteResponseDTO("Paciente adicionado com Sucesso!"));
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> buscarPacientes(){
		List<Paciente> pacientes = pacienteService.consultar();
		
		if(!pacientes.isEmpty()) {
			return ResponseEntity.ok(pacientes);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> consultarPorId(@PathVariable Long id){
		Paciente paciente = pacienteService.consultarPorId(id);
		return ResponseEntity.ok(paciente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarPaciente(@PathVariable Long id,
			@RequestBody @Valid PacienteRequestDTO pacienteDTO){
		Paciente pacienteAtualizado = new Paciente(pacienteDTO);
		pacienteService.atualizarPaciente(id, pacienteAtualizado);
		return ResponseEntity.ok().body(new PacienteResponseDTO("Paciente atualizado com Sucesso!"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Paciente> deletarPaciente(@PathVariable Long id){
		pacienteService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/codigoPaciente/{codigoPaciente}")
	public ResponseEntity<Paciente> consultarPorCodigoPaciente(@PathVariable String codigoPaciente){
		Paciente paciente = pacienteService.consultarPorCodigoPaciente(codigoPaciente);
		return ResponseEntity.ok(paciente);
	}
}
