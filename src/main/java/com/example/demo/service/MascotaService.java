package com.example.demo.service;

import com.example.demo.model.Mascota;
import com.example.demo.model.Usuario; // Asegúrate de importar la clase Usuario
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.UsuarioRepository; // Asegúrate de importar el repositorio de Usuario
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository; // Repositorio para gestionar usuarios

    public MascotaService(MascotaRepository mascotaRepository, UsuarioRepository usuarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.usuarioRepository = usuarioRepository; // Inicialización correcta
    }

    // Método para crear un nuevo recordatorio
    public Mascota crearMascota(Mascota mascota, Long usuarioId) {
        // Buscar el usuario por su ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        mascota.setUsuario(usuario); // Establecer el usuario en el recordatorio
        return mascotaRepository.save(mascota);
    }

    // Método para obtener todos los recordatorios
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

    // Método para obtener un recordatorio por ID
    public Optional<Mascota> obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    // Método para obtener recordatorios por ID de usuario
    public List<Mascota> obtenerMascotaPorUsuarioId(Long usuarioId) {
        return mascotaRepository.findByUsuarioId(usuarioId);
    }

    // Método para eliminar un recordatorio
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}
