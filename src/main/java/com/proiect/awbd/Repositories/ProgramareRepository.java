package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Programare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramareRepository extends JpaRepository<Programare, Long> {
    @Query("SELECT DISTINCT p FROM Programare pr JOIN pr.pacient p WHERE pr.doctor = :doctor")
    List<Pacient> findDistinctPacientiByDoctor(@Param("doctor") Doctor doctor);
    List<Programare> findByPacientId(Long pacientId);
    List<Programare> findByDoctorId(Long doctorId);
}
