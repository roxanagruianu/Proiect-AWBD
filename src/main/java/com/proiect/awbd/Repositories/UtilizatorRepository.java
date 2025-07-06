package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findByUsername(String username);
    boolean existsByUsername(String username);
    @Query("SELECT u FROM Utilizator u LEFT JOIN FETCH u.roluri WHERE u.username = :username")
    Optional<Utilizator> findByUsernameWithRoles(@Param("username") String username);

}