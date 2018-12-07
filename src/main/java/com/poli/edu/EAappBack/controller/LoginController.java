package com.poli.edu.EAappBack.controller;

import com.poli.edu.EAappBack.configuration.Mail;
import com.poli.edu.EAappBack.model.Usuario;
import com.poli.edu.EAappBack.repository.UsuarioRepository;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    Mail mail;

    // Login of a user
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario user) {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(user.getEmail()) && u.getClave().equals(user.getClave()) && u.isEstado())
                .findAny()
                .orElse(null);
    }

    // Recuperar clave of a user
    @PostMapping("/recuperar")
    public int recuperar(@RequestBody Usuario user) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario u = usuarios.stream()
                .filter(usuario -> usuario.getEmail().equals(user.getEmail()))
                .findAny()
                .orElse(null);

        if (u != null) {
            try {
                mail.sendSimpleMessage(u.getEmail(), "EAapp - Recuperación de contraseña",
                        "Estimado/a Usuario \n"
                        + "Muchas Gracias por recurrir a CONTROL AND DEVELOPMENT ONLINE OF CLAPA`Z S.A.S. \n"
                        + "\n"
                        + "A continuación te remitimos tus datos de ingreso. Para acceder a ella introduce las claves que a continuación te detallamos:\n\n"
                        + "Tu contraseña es: " + new String(Base64.decodeBase64(u.getClave())
                        ));
                return 1;
            } catch (MailException exception) {
                exception.printStackTrace();
                return 2;
            }
        } else {
            return 0;
        }
    }

}
