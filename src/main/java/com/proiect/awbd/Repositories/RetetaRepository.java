package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Reteta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetetaRepository extends JpaRepository<Reteta, Long> {
    Optional<Reteta> findByProgramareId(Long programareId);
}