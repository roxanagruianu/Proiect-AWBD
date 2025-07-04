package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
    Optional<Diagnostic> findByProgramareId(Long programareId);
}