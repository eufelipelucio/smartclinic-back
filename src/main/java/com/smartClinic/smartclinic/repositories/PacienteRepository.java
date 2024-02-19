package com.smartClinic.smartclinic.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.smartClinic.smartclinic.entidades.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    UserDetails findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);
    Optional<Paciente> findByCodigoPaciente(String codigoPaciente);
}
 