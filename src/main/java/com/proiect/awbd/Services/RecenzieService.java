package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.RecenzieDTO;

import java.util.List;
import java.util.Optional;

public interface RecenzieService {
    RecenzieDTO save(RecenzieDTO dto);
    Optional<RecenzieDTO> findById(Long id);
    List<RecenzieDTO> findAll();
    List<RecenzieDTO> findByDoctorId(Long doctorId);
    List<RecenzieDTO> findByPacientId(Long pacientId);
    void deleteById(Long id);
}