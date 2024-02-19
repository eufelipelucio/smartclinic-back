package com.smartClinic.smartclinic.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.smartClinic.smartclinic.entidades.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{
 UserDetails findByEmail(String email);
 Boolean existsByEmail(String email);
 Boolean existsByCpf(String email);
 List<Profissional> findByEspecialidades(String especialidades);
 Optional<Profissional> findByCrmProfissional(String crmProfissional);
}
