package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.dtos.PacientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PacientMapper {

    private final ModelMapper modelMapper;

    public PacientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PacientDTO toDTO(Pacient pacient) {
        return modelMapper.map(pacient, PacientDTO.class);
    }

    public Pacient toEntity(PacientDTO dto) {
        Pacient pacient = modelMapper.map(dto, Pacient.class);

        if (pacient.getId() != null && pacient.getId() == 0L) {
            pacient.setId(null);
        }

        return pacient;
    }
}
