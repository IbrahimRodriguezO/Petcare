package com.example.demo.repository;

import com.example.demo.model.Recordatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {
    
    // Método para obtener recordatorios por ID de usuario
    List<Recordatorio> findByUsuarioId(Long usuarioId);
}
