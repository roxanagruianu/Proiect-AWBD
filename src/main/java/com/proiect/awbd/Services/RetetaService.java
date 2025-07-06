package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.RetetaDTO;

import java.util.List;
import java.util.Optional;

public interface RetetaService {
    RetetaDTO save(RetetaDTO dto);
    Optional<RetetaDTO> findById(Long id);
    Optional<RetetaDTO> findByProgramareId(Long programareId);
    List<RetetaDTO> findAll();
    void deleteById(Long id);
    List<RetetaDTO> findByDoctorUsername(String username);

    List<RetetaDTO> findByPacientUsername(String username);
}