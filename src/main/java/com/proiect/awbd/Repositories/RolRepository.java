package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNume(String nume);
    boolean existsByNume(String nume);
}
