package com.example.demo.service;

import com.example.demo.model.Recordatorio;
import com.example.demo.model.Usuario; // Asegúrate de importar la clase Usuario
import com.example.demo.repository.RecordatorioRepository;
import com.example.demo.repository.UsuarioRepository; // Asegúrate de importar el repositorio de Usuario
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordatorioService {

    private final RecordatorioRepository recordatorioRepository;
    private final UsuarioRepository usuarioRepository; // Repositorio para gestionar usuarios

    @Autowired
    public RecordatorioService(RecordatorioRepository recordatorioRepository, UsuarioRepository usuarioRepository) {
        this.recordatorioRepository = recordatorioRepository;
        this.usuarioRepository = usuarioRepository; // Inicialización correcta
    }

    // Método para crear un nuevo recordatorio
    public Recordatorio crearRecordatorio(Recordatorio recordatorio, Long usuarioId) {
        // Buscar el usuario por su ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        recordatorio.setUsuario(usuario); // Establecer el usuario en el recordatorio
        return recordatorioRepository.save(recordatorio);
    }

    // Método para obtener todos los recordatorios
    public List<Recordatorio> obtenerTodosLosRecordatorios() {
        return recordatorioRepository.findAll();
    }

    // Método para obtener un recordatorio por ID
    public Optional<Recordatorio> obtenerRecordatorioPorId(Long id) {
        return recordatorioRepository.findById(id);
    }

    // Método para obtener recordatorios por ID de usuario
    public List<Recordatorio> obtenerRecordatoriosPorUsuarioId(Long usuarioId) {
        return recordatorioRepository.findByUsuarioId(usuarioId);
    }

    // Método para eliminar un recordatorio
    public void eliminarRecordatorio(Long id) {
        recordatorioRepository.deleteById(id);
    }
}
