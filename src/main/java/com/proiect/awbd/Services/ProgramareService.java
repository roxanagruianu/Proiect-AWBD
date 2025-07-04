package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.ProgramareDTO;

import java.util.List;

public interface ProgramareService {
    List<ProgramareDTO> findAll();
    ProgramareDTO findById(Long id);
    void save(ProgramareDTO dto);
    void delete(Long id);
}
