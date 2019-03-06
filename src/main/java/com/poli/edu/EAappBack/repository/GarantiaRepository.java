package com.poli.edu.EAappBack.repository;

import com.poli.edu.EAappBack.model.Garantia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantiaRepository extends JpaRepository<Garantia, Long> {

}
