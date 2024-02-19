package com.smartClinic.smartclinic.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartClinic.smartclinic.DTO.AuthenticationDTO;
import com.smartClinic.smartclinic.DTO.LoginResponseDTO;
import com.smartClinic.smartclinic.entidades.Usuario;
import com.smartClinic.smartclinic.security.TokenService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        String type = getAuthority(auth);

        var token = tokenService.generateToken((Usuario)auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(type,token)); 
    }

    private String getAuthority(Authentication  auth){
        String authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")).toString().replace("ROLE_", "");

        return authorities;
    }
}
