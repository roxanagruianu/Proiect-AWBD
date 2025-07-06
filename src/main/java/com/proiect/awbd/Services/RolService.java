package com.proiect.awbd.Services;

import com.proiect.awbd.dtos.RolDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {
    RolDTO save(RolDTO dto);
    List<RolDTO> findAll();
    Optional<RolDTO> findById(Long id);
    Optional<RolDTO> findByNume(String nume);
}

