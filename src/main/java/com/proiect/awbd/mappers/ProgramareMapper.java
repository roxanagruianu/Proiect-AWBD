package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.ProgramareDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProgramareMapper {

    private final ModelMapper modelMapper;

    public ProgramareMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProgramareDTO toDTO(Programare programare) {
        ProgramareDTO dto = modelMapper.map(programare, ProgramareDTO.class);
        dto.setPacientId(programare.getPacient().getId());
        dto.setDoctorId(programare.getDoctor().getId());
        dto.setClinicaId(programare.getClinica() != null ? programare.getClinica().getId() : null);
        return dto;
    }

    public Programare toEntity(ProgramareDTO dto, Pacient pacient, Doctor doctor, Clinica clinica) {
        Programare programare = modelMapper.map(dto, Programare.class);
        programare.setPacient(pacient);
        programare.setDoctor(doctor);
        programare.setClinica(clinica);
        return programare;
    }
}
