package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.DiagnosticDTO;

import java.util.List;
import java.util.Optional;

public interface DiagnosticService {
    DiagnosticDTO save(DiagnosticDTO dto);
    Optional<DiagnosticDTO> findById(Long id);
    Optional<DiagnosticDTO> findByProgramareId(Long programareId);
    List<DiagnosticDTO> findAll();
    void deleteById(Long id);

    List<DiagnosticDTO> findByDoctorUsername(String username);
    List<DiagnosticDTO> findByPacientUsername(String username);

}
