package com.poli.edu.EAappBack.service;

import com.poli.edu.EAappBack.model.Usuario;
import com.poli.edu.EAappBack.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(string)
                .orElseThrow(() -> new UsernameNotFoundException("Correo " + string + " no existe"));

        List<GrantedAuthority> autority = new ArrayList<>();
        autority.add(new SimpleGrantedAuthority(user.getRole().getNombre()));

        return new User(user.getEmail(), user.getClave(), user.isEstado(), true, true, true, autority);
    }

}
