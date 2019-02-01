package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.exception.ResourceNotFoundException;
import com.poli.edu.EAappBack.model.Usuario;
import com.poli.edu.EAappBack.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Get All Usuarios
    @GetMapping("/usuario")
    public Usuario getUsuarioLogged(Authentication authResult) {
        Usuario user = usuarioRepository.findByEmail(authResult.getPrincipal().toString());
        user.setClave(null);
        return user;
    }

    // Get All Usuarios
    @GetMapping("/usuarios")
    @Secured("ROLE_ADMIN")
    public List<Usuario> getAllUsuarios() throws InterruptedException {
        
        List<Usuario> usuarios = usuarioRepository.findAll().stream()
                .map(user -> {
                    user.setClave(null);
                    return user;
                })
                .collect(Collectors.toList());
        Thread.sleep(3000L);
        return usuarios;
    }

    // Get a Single Usuario
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Long codigo) {
        return usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));
    }

    // Create a new Usuario
    @PostMapping("/usuarios")
    public Usuario createUsuario(@Valid
            @RequestBody Usuario usuario) {
        usuario.setClave(bCryptPasswordEncoder.encode(usuario.getClave()));
        usuario.setEstado(true);
        return usuarioRepository.save(usuario);
    }

    // Update a Usuario
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable(value = "id") Long codigo,
            @Valid
            @RequestBody Usuario usuarioDetails) {
        
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));
        
        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellidos(usuarioDetails.getApellidos());
        usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
        usuario.setDireccion(usuarioDetails.getDireccion());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setClave(bCryptPasswordEncoder.encode(usuario.getClave()));
        
        usuarioRepository.save(usuario);
        
        return ResponseEntity.ok().build();
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

    // Recuperar clave of a user
//    @PostMapping("/recuperar")
//    public int recuperar(@RequestBody Usuario user) {
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        Usuario u = usuarios.stream()
//                .filter(usuario -> usuario.getEmail().equals(user.getEmail()))
//                .findAny()
//                .orElse(null);
//
//        if (u != null) {
//            try {
//                mail.sendSimpleMessage(u.getEmail(), "EAapp - Recuperación de contraseña",
//                        "Estimado/a Usuario \n"
//                        + "Muchas Gracias por recurrir a CONTROL AND DEVELOPMENT ONLINE OF CLAPA`Z S.A.S. \n"
//                        + "\n"
//                        + "A continuación te remitimos tus datos de ingreso. Para acceder a ella introduce las claves que a continuación te detallamos:\n\n"
//                        + "Tu contraseña es: " + new String(Base64.decodeBase64(u.getClave())
//                        ));
//                return 1;
//            } catch (MailException exception) {
//                exception.printStackTrace();
//                return 2;
//            }
//        } else {
//            return 0;
//        }
//    }
}
