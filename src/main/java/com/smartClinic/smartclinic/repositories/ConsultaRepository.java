package com.smartClinic.smartclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartClinic.smartclinic.entidades.Consulta;
import com.smartClinic.smartclinic.entidades.Paciente;
import com.smartClinic.smartclinic.entidades.Profissional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	List<Consulta> findByPaciente(Paciente paciente);
	List<Consulta> findByProfissional(Profissional profissional);
}
