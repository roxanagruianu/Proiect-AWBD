package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.data_model.Reteta;
import com.proiect.awbd.dtos.RetetaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RetetaMapper {

    private final ModelMapper modelMapper;

    public RetetaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RetetaDTO toDTO(Reteta reteta) {
        RetetaDTO dto = modelMapper.map(reteta, RetetaDTO.class);
        dto.setProgramareId(reteta.getProgramare().getId());
        return dto;
    }

    public Reteta toEntity(RetetaDTO dto, Programare programare) {
        Reteta reteta = modelMapper.map(dto, Reteta.class);
        reteta.setProgramare(programare);
        return reteta;
    }
}
