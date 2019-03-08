package com.poli.edu.EAappBack.repository;

import com.poli.edu.EAappBack.model.Causa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CausaRepository extends JpaRepository<Causa, Long> {

}
