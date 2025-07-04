package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Diagnostic;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.DiagnosticDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticMapper {

    private final ModelMapper modelMapper;

    public DiagnosticMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DiagnosticDTO toDTO(Diagnostic diagnostic) {
        DiagnosticDTO dto = modelMapper.map(diagnostic, DiagnosticDTO.class);
        dto.setProgramareId(diagnostic.getProgramare().getId());
        return dto;
    }

    public Diagnostic toEntity(DiagnosticDTO dto, Programare programare) {
        Diagnostic diagnostic = modelMapper.map(dto, Diagnostic.class);
        diagnostic.setProgramare(programare);
        return diagnostic;
    }
}
