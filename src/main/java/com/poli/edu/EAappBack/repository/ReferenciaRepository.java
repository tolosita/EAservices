package com.poli.edu.EAappBack.repository;

import com.poli.edu.EAappBack.model.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenciaRepository extends JpaRepository<Referencia, Long> {

}
