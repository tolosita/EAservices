package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.exception.ResourceNotFoundException;
import com.poli.edu.EAappBack.model.Usuario;
import com.poli.edu.EAappBack.repository.UsuarioRepository;
import com.poli.edu.EAappBack.service.MailService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MailService mail;

    // Get user logged
    @GetMapping("/usuario")
    public Usuario getUsuarioLogged(Authentication authResult) {
        Usuario user = usuarioRepository.findByEmail(authResult.getPrincipal().toString())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", authResult.getPrincipal()));
        user.setClave(null);
        return user;
    }

    // Get All Usuarios
    @GetMapping("/usuarios")
    @Secured("ROLE_ADMIN")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .filter(Usuario::isEstado)
                .collect(Collectors.toList());
    }

    // Get a Single Usuario
    @GetMapping("/usuarios/{id}")
    @Secured("ROLE_ADMIN")
    public Usuario getUsuarioById(@PathVariable(value = "id") Long codigo) {
        Usuario user = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));
        user.setClave(null);
        return user;
    }

    // Create and Update a new Usuario
    @PostMapping("/usuarios")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuarioDetails) throws Exception {
        Usuario userExist = usuarioRepository.findByEmail(usuarioDetails.getEmail()).orElse(null);

        if (userExist != null) {
            throw new Exception("El correo ya se encuentra registrado en el sistema");
        }

        usuarioDetails.setClave(bCryptPasswordEncoder.encode(usuarioDetails.getClave()));
        usuarioDetails.setEstado(true);

        usuarioRepository.save(usuarioDetails);

        return ResponseEntity.ok().build();
    }

    // Update a new Usuario
    @PutMapping("/usuarios/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> updateUsuario(@PathVariable(value = "id") Long codigo, @Valid @RequestBody Usuario usuarioDetails) throws Exception {
        Usuario userExist = usuarioRepository.findByEmail(usuarioDetails.getEmail()).orElse(null);
        Usuario userUpdate = usuarioRepository.findById(codigo).orElse(null);

        if (userExist != null && !usuarioDetails.getEmail().equals(userUpdate.getEmail())) {
            throw new Exception("El correo ya se encuentra registrado en el sistema");
        }

        userUpdate.setNombre(usuarioDetails.getNombre());
        userUpdate.setApellidos(usuarioDetails.getApellidos());
        userUpdate.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
        userUpdate.setDireccion(usuarioDetails.getDireccion());
        userUpdate.setEmail(usuarioDetails.getEmail());
        userUpdate.setClave(bCryptPasswordEncoder.encode(usuarioDetails.getClave()));
        userUpdate.setRole(usuarioDetails.getRole());

        usuarioRepository.save(userExist);

        return ResponseEntity.ok().build();
    }

    // Delete a Usuario
    @DeleteMapping("/usuarios/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long codigo) {
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));

        usuario.setEstado(false);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    // Recuperar clave of a user
    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperar(@RequestBody String email) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        if (usuario != null) {
            try {
                mail.sendSimpleMessage(usuario.getEmail(), "EAapp - Recuperación de contraseña",
                        "Estimado/a Usuario \n"
                        + "Muchas Gracias por recurrir a CONTROL AND DEVELOPMENT ONLINE OF CLAPA`Z S.A.S. \n"
                        + "\n"
                        + "A continuación te remitimos tus datos de ingreso. Para acceder a ella introduce las claves que a continuación te detallamos:\n\n"
                        + "Tu contraseña es: " + usuario.getClave()
                );
                return ResponseEntity.ok().build();
            } catch (MailException exception) {
                throw new Exception(exception.getMessage());
            }
        } else {
            throw new Exception("El correo no se encuentra registrado en el sistema");
        }
    }
}
