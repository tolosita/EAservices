package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.exception.ResourceNotFoundException;
import com.poli.edu.EAappBack.model.Cliente;
import com.poli.edu.EAappBack.repository.ClienteRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // Get a Single Cliente
    @GetMapping("/clientes/{id}")
    public Cliente getClienteById(@PathVariable(value = "id") Long codigo) {
        Cliente client = clienteRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", codigo));
        return client;
    }

    // Create a new Cliente
    @PostMapping("/clientes")
    public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente clienteDetails) throws Exception {
        Cliente clientExist = clienteRepository.findByNroDocumento(clienteDetails.getNroDocumento()).orElse(null);

        if (clientExist != null) {
            throw new Exception("El documento ya existe");
        }

        clienteDetails.setEstado(true);
        clienteRepository.save(clienteDetails);

        return ResponseEntity.ok().build();
    }

    // Update a new Cliente
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable(value = "id") Long codigo, @Valid @RequestBody Cliente clienteDetails) throws Exception {
        Cliente clientExist = clienteRepository.findByNroDocumento(clienteDetails.getNroDocumento()).orElse(null);
        Cliente clientUpdate = clienteRepository.findById(codigo).orElse(null);

        if (clientExist != null && !clienteDetails.getNroDocumento().equals(clientUpdate.getNroDocumento())) {
            throw new Exception("El documento ya existe");
        }

        clientUpdate.setTipoDocumento(clienteDetails.getTipoDocumento());
        clientUpdate.setNombre(clienteDetails.getNroDocumento());
        clientUpdate.setNombre(clienteDetails.getNombre());
        clientUpdate.setApellidos(clienteDetails.getApellidos());
        clientUpdate.setEmail(clienteDetails.getEmail());
        clientUpdate.setTelefono(clienteDetails.getTelefono());
        clientUpdate.setCelular(clienteDetails.getCelular());
        clientUpdate.setDireccion(clienteDetails.getDireccion());
        clientUpdate.setPais(clienteDetails.getPais());
        clientUpdate.setCiudad(clienteDetails.getCiudad());

        clienteRepository.save(clientUpdate);

        return ResponseEntity.ok().build();
    }

    // Delete a Cliente
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long codigo) {
        Cliente cliente = clienteRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", codigo));

        cliente.setEstado(false);
        clienteRepository.save(cliente);

        return ResponseEntity.ok().build();
    }

}
