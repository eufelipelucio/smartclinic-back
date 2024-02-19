package com.smartClinic.smartclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartClinic.smartclinic.repositories.AdministradorRepository;
import com.smartClinic.smartclinic.repositories.PacienteRepository;
import com.smartClinic.smartclinic.repositories.ProfissionalRepository;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Autowired
    AdministradorRepository administradorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserDetails paciente = pacienteRepository.findByEmail(username);
        if (paciente != null) {
            return paciente;
        }

        UserDetails profissional = profissionalRepository.findByEmail(username);
        if (profissional != null) {
            return profissional;
        }
        UserDetails administrador = administradorRepository.findByEmail(username);
        if (administrador != null) {
            return administrador;
        }
        return null;
    }
    
}
