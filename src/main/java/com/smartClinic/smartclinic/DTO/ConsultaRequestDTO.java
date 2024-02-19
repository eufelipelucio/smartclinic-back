package com.smartClinic.smartclinic.DTO;


import java.time.LocalDate;
import java.time.LocalTime;

import com.smartClinic.smartclinic.entidades.Paciente;
import com.smartClinic.smartclinic.entidades.Profissional;
import com.smartClinic.smartclinic.enums.StatusConsulta;

 public record ConsultaRequestDTO(
        LocalDate data,
        LocalTime hora,
        String protocolo,
        StatusConsulta status_consulta,
        String observacao,
        String observacao_prontuario,
        Paciente paciente,
        Profissional profissional
) {
   
    
}
