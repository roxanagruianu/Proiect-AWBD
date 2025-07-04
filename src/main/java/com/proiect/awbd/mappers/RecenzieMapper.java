package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Recenzie;
import com.proiect.awbd.dtos.RecenzieDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RecenzieMapper {

    private final ModelMapper modelMapper;

    public RecenzieMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RecenzieDTO toDTO(Recenzie recenzie) {
        RecenzieDTO dto = modelMapper.map(recenzie, RecenzieDTO.class);
        dto.setPacientId(recenzie.getPacient().getId());
        dto.setDoctorId(recenzie.getDoctor().getId());
        return dto;
    }

    public Recenzie toEntity(RecenzieDTO dto, Pacient pacient, Doctor doctor) {
        Recenzie recenzie = modelMapper.map(dto, Recenzie.class);
        recenzie.setPacient(pacient);
        recenzie.setDoctor(doctor);
        return recenzie;
    }
}
