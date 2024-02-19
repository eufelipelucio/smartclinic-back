package com.smartClinic.smartclinic.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.smartClinic.smartclinic.entidades.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    UserDetails findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String email);
}
