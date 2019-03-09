package com.poli.edu.EAappBack.repository;

import com.poli.edu.EAappBack.model.GarantiaCausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantiaCausaRepository extends JpaRepository<GarantiaCausa, Long> {

}
