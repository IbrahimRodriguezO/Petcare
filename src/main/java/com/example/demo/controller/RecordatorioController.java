package com.example.demo.controller;

import com.example.demo.model.Recordatorio;
import com.example.demo.service.RecordatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/petcare/recordatorios")
public class RecordatorioController {

    private final RecordatorioService recordatorioService;

    public RecordatorioController(RecordatorioService recordatorioService) {
        this.recordatorioService = recordatorioService;
    }

    // Crear un nuevo recordatorio
    @PostMapping("/{usuarioId}") // Cambiado para incluir el ID del usuario en la URL
    public ResponseEntity<Recordatorio> crearRecordatorio(@PathVariable Long usuarioId, @RequestBody Recordatorio recordatorio) {
        Recordatorio nuevoRecordatorio = recordatorioService.crearRecordatorio(recordatorio, usuarioId);
        return ResponseEntity.ok(nuevoRecordatorio);
    }

    // Obtener todos los recordatorios
    @GetMapping
    public ResponseEntity<List<Recordatorio>> obtenerTodosLosRecordatorios() {
        List<Recordatorio> recordatorios = recordatorioService.obtenerTodosLosRecordatorios();
        return ResponseEntity.ok(recordatorios);
    }

    // Obtener un recordatorio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Recordatorio> obtenerRecordatorioPorId(@PathVariable Long id) {
        return recordatorioService.obtenerRecordatorioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener recordatorios por ID de usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Recordatorio>> obtenerRecordatoriosPorUsuarioId(@PathVariable Long usuarioId) {
        List<Recordatorio> recordatorios = recordatorioService.obtenerRecordatoriosPorUsuarioId(usuarioId);
        return ResponseEntity.ok(recordatorios);
    }

    // Eliminar un recordatorio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecordatorio(@PathVariable Long id) {
        recordatorioService.eliminarRecordatorio(id);
        return ResponseEntity.noContent().build();
    }
}
