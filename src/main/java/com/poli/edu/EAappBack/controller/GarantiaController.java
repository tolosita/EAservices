package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.exception.ResourceNotFoundException;
import com.poli.edu.EAappBack.model.Causa;
import com.poli.edu.EAappBack.model.Garantia;
import com.poli.edu.EAappBack.model.GarantiaCausa;
import com.poli.edu.EAappBack.model.Referencia;
import com.poli.edu.EAappBack.repository.GarantiaCausaRepository;
import com.poli.edu.EAappBack.repository.GarantiaRepository;
import com.poli.edu.EAappBack.repository.ReferenciaRepository;
import com.poli.edu.EAappBack.repository.UsuarioRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    GarantiaCausaRepository garantiaCausaRepository;

    @Autowired
    ReferenciaRepository referenciaRepository;

    // Get All Garantias
    @GetMapping("/garantias")
    public List<Garantia> getAllGarantias() {
        return garantiaRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    // Get a Single Garantia
    @GetMapping("/garantias/{id}")
    public Garantia getGarantiaById(@PathVariable(value = "id") Long codigo) {
        Garantia garantia = garantiaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Garantia", "id", codigo));
        return garantia;
    }

    // Create a new Garantia
    @PostMapping("/garantias")
    public Garantia createGarantia(@Valid @RequestBody Garantia garantiaDetails, Authentication authResult) throws Exception {
        garantiaDetails.setUsuario(usuarioRepository.findByEmail(authResult.getPrincipal().toString()).orElse(null));
        return garantiaRepository.save(garantiaDetails);
    }

    // Update a new Garantia
    @PutMapping("/garantias/{id}")
    public Garantia updateGarantia(@Valid @RequestBody Garantia garantiaDetails, @PathVariable(value = "id") Long codigo, Authentication authResult) throws Exception {
        garantiaDetails.setUsuario(usuarioRepository.findByEmail(authResult.getPrincipal().toString()).orElse(null));

        referenciaRepository.findAll().stream()
                .filter(ref -> ref.getGarantia().getId().equals(codigo))
                .forEach(referenciaRepository::delete);

        garantiaCausaRepository.findAll().stream()
                .filter(garantiaCausa -> garantiaCausa.getGarantia().getId().equals(codigo))
                .forEach(garantiaCausaRepository::delete);

        return garantiaRepository.save(garantiaDetails);
    }

    @GetMapping("/referencias/{id}")
    public List<Referencia> getRefereciaById(@PathVariable(value = "id") Long codigo) {
        return referenciaRepository.findAll().stream()
                .filter(ref -> ref.getGarantia().getId().equals(codigo))
                .collect(Collectors.toList());
    }

    @PostMapping("/referencias/{id}")
    public ResponseEntity<?> createReferencias(@Valid @RequestBody Referencia referenciaDetails, @PathVariable(value = "id") Long id) throws Exception {
        Garantia garantia = garantiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garantia", "id", id));

        referenciaDetails.setGarantia(garantia);
        referenciaRepository.save(referenciaDetails);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/causas/{id}")
    public List<Causa> getCausasById(@PathVariable(value = "id") Long codigo) {
        return garantiaCausaRepository.findAll().stream()
                .filter(garantiaCausa -> garantiaCausa.getGarantia().getId().equals(codigo))
                .map(garantiaCausa -> garantiaCausa.getCausa())
                .collect(Collectors.toList());
    }

    @PostMapping("/causas/{id}")
    public ResponseEntity<?> createCausas(@Valid @RequestBody Causa causa, @PathVariable(value = "id") Long id) throws Exception {
        Garantia garantia = garantiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garantia", "id", id));

        GarantiaCausa gc = new GarantiaCausa();
        gc.setCausa(causa);
        gc.setGarantia(garantia);

        garantiaCausaRepository.save(gc);

        return ResponseEntity.ok().build();
    }
}
