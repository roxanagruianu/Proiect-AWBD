package com.proiect.awbd.Repositories;

import com.proiect.awbd.data_model.Recenzie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecenzieRepository extends JpaRepository<Recenzie, Long> {
}
