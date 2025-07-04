package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.dtos.ClinicaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClinicaMapper {

    private final ModelMapper modelMapper;

    public ClinicaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClinicaDTO toDTO(Clinica clinica) {
        return modelMapper.map(clinica, ClinicaDTO.class);
    }

    public Clinica toEntity(ClinicaDTO clinicaDTO) {
        return modelMapper.map(clinicaDTO, Clinica.class);
    }
}
