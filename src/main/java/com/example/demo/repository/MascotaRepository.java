package com.example.demo.repository;

import com.example.demo.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    
    // Método para obtener recordatorios por ID de usuario
    List<Mascota> findByUsuarioId(Long usuarioId);
}
