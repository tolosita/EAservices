package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.model.Causa;
import com.poli.edu.EAappBack.model.Role;
import com.poli.edu.EAappBack.model.TipoDocumento;
import com.poli.edu.EAappBack.repository.CausaRepository;
import com.poli.edu.EAappBack.repository.RoleRepository;
import com.poli.edu.EAappBack.repository.TipoDocumentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alejandro Diez
 */
@RestController
@RequestMapping("/api")
public class SharedController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    CausaRepository causaRepository;

    /**
     * Metodo que devuelve los roles que estan en la base de datos
     *
     * @return listado de roles de la aplicacion
     */
    @GetMapping("/roles")
    @Secured("ROLE_ADMIN")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/tipoDocumentos")
    @Secured("ROLE_ADMIN")
    public List<TipoDocumento> getAllTipoDocumentos() {
        return tipoDocumentoRepository.findAll();
    }

    @GetMapping("/causas")
    public List<Causa> getAllCausas() {
        return causaRepository.findAll();
    }

}
