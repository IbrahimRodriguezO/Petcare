package com.example.demo.controller;

import com.example.demo.model.Usuario;

import com.example.demo.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/petcare/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List <com.example.demo.model.Usuario>getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.demo.model.Usuario> getUsuarioById(@PathVariable Long id){
        Optional<com.example.demo.model.Usuario> usuario=usuarioService.getUsuarioById(id);

        return usuario.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<com.example.demo.model.Usuario> createUsuario(@RequestBody com.example.demo.model.Usuario usuario){

        com.example.demo.model.Usuario newUsuario= usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){

        usuarioService.deleteUsuario(id);

        return ResponseEntity.noContent().build();
    }

    // Método de autenticación (login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuario = usuarioService.authenticate(loginRequest.getCorreo(), loginRequest.getContrasenia());

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }


}
