package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {
    Optional<Pacient> findByEmail(String email);

    @Query("SELECT p FROM Pacient p WHERE p.utilizator.username = :username")
    Optional<Pacient> findByUsername(@Param("username") String username);
}
