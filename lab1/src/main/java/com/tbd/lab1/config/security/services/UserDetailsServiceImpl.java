package com.tbd.lab1.config.security.services;

import com.tbd.lab1.entities.UsuarioEntity;
import com.tbd.lab1.repositories.UsuarioRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepositoryImpl usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsuarioEntity usuario = usuarioRepository.findByEmail(email);
        String encodedPass = passwordEncoder.encode(usuario.getPassword());

        if (usuario == null) {
            throw new UsernameNotFoundException(email);
        }
        return User
                .withUsername(email)
                .password(encodedPass)
                .roles(usuario.getRol())
                .build();
    }
}
