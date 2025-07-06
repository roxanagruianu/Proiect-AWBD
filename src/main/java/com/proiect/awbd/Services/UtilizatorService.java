package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.UtilizatorDTO;

import java.util.List;
import java.util.Optional;

public interface UtilizatorService {
    UtilizatorDTO save(UtilizatorDTO dto);
    Optional<UtilizatorDTO> findByUsername(String username);
    List<UtilizatorDTO> findAll();
    Optional<UtilizatorDTO> findById(Long id);
    void deleteById(Long id);
    UtilizatorDTO update(UtilizatorDTO dto);
}
