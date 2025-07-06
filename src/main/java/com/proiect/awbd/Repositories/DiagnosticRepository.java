package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Diagnostic;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
    Optional<Diagnostic> findByProgramareId(Long programareId);

    @Query("SELECT d FROM Diagnostic d WHERE d.programare.doctor = :doctor")
    List<Diagnostic> findByDoctor(@Param("doctor") Doctor doctor);

    @Query("SELECT d FROM Diagnostic d WHERE d.programare.pacient = :pacient")
    List<Diagnostic> findByPacient(@Param("pacient") Pacient pacient);

}