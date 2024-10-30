package com.example.demo.repository;

import com.example.demo.model.Usuario;

import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

     Optional<Usuario> findByCorreoAndContrasenia(String correo, String contrasenia);
}
