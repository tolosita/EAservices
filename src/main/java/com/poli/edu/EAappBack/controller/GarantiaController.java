package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.model.Garantia;
import com.poli.edu.EAappBack.repository.GarantiaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GarantiaController {

    @Autowired
    GarantiaRepository garantiaRepository;

    // Get All Garantias
    @GetMapping("/garantias")
    public List<Garantia> getAllGarantias() {
        return garantiaRepository.findAll().stream()
                .collect(Collectors.toList());
    }
}
