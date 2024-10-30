package com.example.demo.controller;
import com.example.demo.model.Mascota;
import com.example.demo.service.MascotaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/petcare/mascotas")
public class MascotaController {
    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    // Crear un nuevo recordatorio
    @PostMapping("/{usuarioId}") // Cambiado para incluir el ID del usuario en la URL
    public ResponseEntity<Mascota> crearRecordatorio(@PathVariable Long usuarioId, @RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.crearMascota(mascota, usuarioId);
        return ResponseEntity.ok(nuevaMascota);
    }

    // Obtener todos los recordatorios
    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerTodasLasMascotas() {
        List<Mascota> mascotas = mascotaService.obtenerTodasLasMascotas();
        return ResponseEntity.ok(mascotas);
    }

    // Obtener una mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        return mascotaService.obtenerMascotaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener mascota por ID de usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Mascota>> obtenerMascotasPorUsuarioId(@PathVariable Long usuarioId) {
        List<Mascota> mascotas = mascotaService.obtenerMascotaPorUsuarioId(usuarioId);
        return ResponseEntity.ok(mascotas);
    }

    // Eliminar una mascota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
