package com.smartClinic.smartclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartClinic.smartclinic.entidades.Usuario;
import com.smartClinic.smartclinic.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("perfil")
public class PerfilController {
    
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(Authentication auth){
        var usuario = (Usuario)auth.getPrincipal();
 //       UserDetails user = userService.loadUserByUsername(usuario.getUsername());
 //       PerfilResponseDTO perfilResponseDTO = new PerfilResponseDTO(
            // usuario.getNome(),
            // usuario.getId(),
            // usuario.getSobrenome(),
            // usuario.getCpf(),
            // usuario.getDataNascimento(),
            // usuario.getTelefone(),
            // usuario.getCelular(),
            // usuario.getEmail(),
            // usuario.getEndereco()       
            // );
        return ResponseEntity.ok().body(usuario);
    }
}
