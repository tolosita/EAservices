package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.exception.ResourceNotFoundException;
import com.poli.edu.EAappBack.model.Garantia;
import com.poli.edu.EAappBack.repository.GarantiaRepository;
import com.poli.edu.EAappBack.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GarantiaController {
    
    @Autowired
    GarantiaRepository garantiaRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;

    // Get All Garantias
    @GetMapping("/garantias")
    public List<Garantia> getAllGarantias() {
        return garantiaRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    // Create a new Garantia
    @PostMapping("/garantias")
    public ResponseEntity<?> createGarantia(@Valid @RequestBody Garantia usuarioDetails, Authentication authResult) throws Exception {
        usuarioDetails.setUsuario(usuarioRepository.findByEmail(authResult.getPrincipal().toString()).orElse(null));
        garantiaRepository.save(usuarioDetails);
        return ResponseEntity.ok().build();
    }
}
