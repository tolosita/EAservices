package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.model.Cliente;
import com.poli.edu.EAappBack.repository.ClienteRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    // Get All Clientes
    @GetMapping("/clientes")
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll().stream()
                .filter(Cliente::isEstado)
                .collect(Collectors.toList());
    }

    
    
}
