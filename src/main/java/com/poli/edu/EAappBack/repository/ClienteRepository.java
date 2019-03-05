package com.poli.edu.EAappBack.repository;

import com.poli.edu.EAappBack.model.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNroDocumento(String documento);

}
