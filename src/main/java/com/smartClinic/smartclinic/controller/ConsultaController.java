package com.smartClinic.smartclinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.smartClinic.smartclinic.DTO.ConsultaRequestDTO;
import com.smartClinic.smartclinic.DTO.ConsultaResponseDTO;
import com.smartClinic.smartclinic.entidades.Consulta;
import com.smartClinic.smartclinic.entidades.Paciente;
import com.smartClinic.smartclinic.entidades.Profissional;
import com.smartClinic.smartclinic.service.ConsultaService;
import com.smartClinic.smartclinic.service.PacienteService;
import com.smartClinic.smartclinic.service.ProfissionalService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> salvarConsulta(@RequestBody @Valid ConsultaRequestDTO consulta){
		Consulta consultaSalvo = new Consulta(consulta);
		consultaService.salvarConsulta(consultaSalvo);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ConsultaResponseDTO("Consulta agendada com sucesso"));
	}
	
	@GetMapping
	public ResponseEntity<List<Consulta>> buscarConsulta(){
		List<Consulta> consultas = consultaService.consultar();
		
		if(!consultas.isEmpty()) {
			return ResponseEntity.ok().body(consultas);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> consultarPorId(@PathVariable Long id){
		Consulta consulta = consultaService.consultarPorId(id);
		return ResponseEntity.ok(consulta);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarConsulta(@PathVariable Long id, 
			@RequestBody @Valid ConsultaRequestDTO novaConsulta){
			Consulta consultaAtualizada = new Consulta(novaConsulta);
		consultaService.atualizarConsulta(id, consultaAtualizada);
		return ResponseEntity.ok().body(new ConsultaResponseDTO("Consulta atualizada com sucesso"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Consulta> excluirConsulta(@PathVariable Long id){
		consultaService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/paciente/{id}")
	public ResponseEntity<?> listarConsultasPacientes(@PathVariable Long id){
		Paciente paciente = pacienteService.consultarPorId(id);
		return ResponseEntity.ok(consultaService.listarConsultasPacientes(paciente));
	}

	@GetMapping("/profissional/{id}")
	public ResponseEntity<?> listarConsultasProfissionais(@PathVariable Long id){
		Profissional profissional = profissionalService.consultarPorId(id);
		return ResponseEntity.ok(consultaService.listarConsultasProfissionais(profissional));
	}
}