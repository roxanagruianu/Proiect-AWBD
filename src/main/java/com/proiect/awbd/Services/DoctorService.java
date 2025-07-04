package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.DoctorDTO;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> findAll();
    DoctorDTO findById(Long id);
    DoctorDTO save(DoctorDTO dto);
    void deleteById(Long id);
}
