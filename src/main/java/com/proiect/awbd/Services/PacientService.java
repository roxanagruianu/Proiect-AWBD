package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.PacientDTO;

import java.util.List;

public interface PacientService {
    List<PacientDTO> findAll();
    PacientDTO findById(Long id);
    PacientDTO save(PacientDTO pacient);
    void deleteById(Long id);
}
