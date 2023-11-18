package com.tbd.lab1.controllers;

import com.tbd.lab1.config.security.model.AuthenticationReq;
import com.tbd.lab1.config.security.model.TokenInfo;
import com.tbd.lab1.config.security.services.JwtUtilService;
import com.tbd.lab1.entities.UsuarioEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.tbd.lab1.repositories.UsuarioRepositoryImpl;
import org.springframework.security.authentication.AuthenticationManager;
import com.tbd.lab1.config.security.services.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.HashMap;
import java.util.Map;


@CrossOrigin("*")
@RestController()
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    UserDetailsServiceImpl usuarioDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private UsuarioRepositoryImpl usuarioRepository;

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody AuthenticationReq authenticationReq){
        try{
            logger.info(authenticationReq.getEmail());
            logger.info(authenticationReq.getClave());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationReq.getEmail(),
                            authenticationReq.getClave()));
            final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                    authenticationReq.getEmail());

            final String jwt = jwtUtilService.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("status", "ok");
            response.put("token", jwt);
            response.put("id", usuarioRepository.findByEmail(authenticationReq.getEmail()).getId().toString());
            response.put("rol", usuarioRepository.findByEmail(authenticationReq.getEmail()).getRol());

            return ResponseEntity.ok(response);
        }
        catch (RuntimeException e){
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("token", "error");

            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UsuarioEntity usuario){
        try{

            UsuarioEntity existingUser = usuarioRepository.findByEmail(usuario.getEmail());
            if (existingUser != null) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                return ResponseEntity.ok(response);
            }
            usuarioRepository.register(usuario);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getEmail(),
                            usuario.getPassword()));
            final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                    usuario.getEmail());

            final String jwt = jwtUtilService.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("status", "registrado");

            return ResponseEntity.ok(response);
        }catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Error en el registro.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/api/usuario/{id}")
    public UsuarioEntity getUser(@PathVariable Long id){
        return usuarioRepository.findById(id);
    }

    @PutMapping("/api/usuario/actualizar")
    public ResponseEntity<Void> update(@RequestBody UsuarioEntity usuario){
        usuarioRepository.update(usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
