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

import com.smartClinic.smartclinic.DTO.AdministradorRequestDTO;
import com.smartClinic.smartclinic.DTO.AdministradorResponseDTO;
import com.smartClinic.smartclinic.DTO.PacienteResponseDTO;
import com.smartClinic.smartclinic.entidades.Administrador;
import com.smartClinic.smartclinic.service.AdministradorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/administradores")
public class AdministradorController {
	
	@Autowired
	private AdministradorService administradorService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvarAdministrador(@RequestBody @Valid AdministradorRequestDTO administradorDTO){
		String encryptedPassword = new BCryptPasswordEncoder().encode(administradorDTO.senha());
		Administrador administradorSalvo = new Administrador(administradorDTO);

		administradorSalvo.setSenha(encryptedPassword);
		administradorService.salvarAdministrador(administradorSalvo);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new AdministradorResponseDTO("Administrador Adicionado com Sucesso!"));
	}
	
	@GetMapping
	public ResponseEntity<List<Administrador>> buscarAdministradores(){
		List<Administrador> administradores = administradorService.consultar();
		
		if(!administradores.isEmpty()) {
			return ResponseEntity.ok(administradores);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Administrador> consultarPorId(@PathVariable Long id){
		Administrador administrador = administradorService.consultarPorId(id);
		return ResponseEntity.ok(administrador);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarAdministrador(@PathVariable Long id,
			@RequestBody @Valid AdministradorRequestDTO administradorDTO){
		Administrador administradorAtualizado = new Administrador(administradorDTO);
		administradorService.atualizarAdministrador(id, administradorAtualizado);
		return ResponseEntity.ok().body(new PacienteResponseDTO("Administrador atualizado com Sucesso!"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Administrador> deletarAdministrador(@PathVariable Long id){
		administradorService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
