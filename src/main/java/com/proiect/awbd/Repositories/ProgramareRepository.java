package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Programare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramareRepository extends JpaRepository<Programare, Long> {
}
