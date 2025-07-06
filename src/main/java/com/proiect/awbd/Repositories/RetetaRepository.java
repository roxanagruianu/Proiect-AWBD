package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Reteta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RetetaRepository extends JpaRepository<Reteta, Long> {
    Optional<Reteta> findByProgramareId(Long programareId);

    @Query("SELECT r FROM Reteta r WHERE r.programare.pacient.id = :pacientId")
    List<Reteta> findByPacientId(@Param("pacientId") Long pacientId);

    @Query("SELECT r FROM Reteta r WHERE r.programare.doctor.utilizator.username = :doctorUsername")
    List<Reteta> findByDoctorUsername(@Param("doctorUsername") String doctorUsername);

    @Query("SELECT r FROM Reteta r WHERE r.programare.pacient.utilizator.username = :pacientUsername")
    List<Reteta> findByPacientUsername(@Param("pacientUsername") String pacientUsername);
}