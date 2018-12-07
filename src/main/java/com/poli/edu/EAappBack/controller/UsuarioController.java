package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.exception.ResourceNotFoundException;
import com.poli.edu.EAappBack.model.Role;
import com.poli.edu.EAappBack.model.Usuario;
import com.poli.edu.EAappBack.repository.UsuarioRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.poli.edu.EAappBack.repository.RoleRepository;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository cargoRepository;

    // Get All Usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Get All Cargos
    @GetMapping("/cargos")
    public List<Role> getAllCargos() {
        return cargoRepository.findAll();
    }

    // Get a Single Usuario
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Long codigo) {
        return usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));
    }

    // Create a new Usuario
    @PostMapping("/usuarios")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        usuario.setEstado(true);
        return usuarioRepository.save(usuario);
    }

    // Update a Usuario
    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody Usuario usuarioDetails) {

        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellidos(usuarioDetails.getApellidos());
        usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
        usuario.setDireccion(usuarioDetails.getDireccion());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setClave(usuarioDetails.getClave());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return updatedUsuario;
    }

    // Delete a Usuario
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long codigo) {
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));

        usuario.setEstado(false);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
