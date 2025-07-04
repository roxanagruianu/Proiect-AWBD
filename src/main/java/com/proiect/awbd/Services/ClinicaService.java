package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.ClinicaDTO;

import java.util.List;

public interface ClinicaService {
    List<ClinicaDTO> findAll();
    ClinicaDTO findById(Long id);
    ClinicaDTO save(ClinicaDTO clinicaDTO);
    void deleteById(Long id);
}
