package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);

    @Query("SELECT d FROM Doctor d WHERE d.utilizator.username = :username")
    Optional<Doctor> findByUsername(@Param("username") String username);
}
