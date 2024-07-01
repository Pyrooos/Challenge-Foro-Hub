package com.alura.forohub.controller;

import com.alura.forohub.topico.Topico;
import com.alura.forohub.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<?> crearTopico(@Valid @RequestBody Topico topico) {
        // Verificar si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un tópico con el mismo título y mensaje.");
        }
        // Guardar el nuevo tópico en la base de datos
        Topico nuevoTopico = topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listarTodosLosTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            return ResponseEntity.ok(topico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún tópico con el ID proporcionado: " + id);
        }
    }

    // Método opcional para búsqueda por curso y año
    @GetMapping("/buscar")
    public ResponseEntity<List<Topico>> buscarPorCursoYAnio(
            @RequestParam(value = "curso") String curso,
            @RequestParam(value = "anio") int anio) {
        List<Topico> topicos = topicoRepository.findByCursoAndAnio(curso, anio);
        return ResponseEntity.ok(topicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(
            @PathVariable Long id,
            @Valid @RequestBody Topico topicoActualizado) {

        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            // Validar si se está intentando actualizar con un título y mensaje que ya existen
            if (!topicoRepository.existsByTituloAndMensajeAndIdNot(
                    topicoActualizado.getTitulo(),
                    topicoActualizado.getMensaje(),
                    id)) {
                // Actualizar los datos del tópico existente
                Topico topicoExistente = optionalTopico.get();
                topicoExistente.setTitulo(topicoActualizado.getTitulo());
                topicoExistente.setMensaje(topicoActualizado.getMensaje());
                topicoExistente.setStatus(topicoActualizado.getStatus());
                topicoExistente.setAutor(topicoActualizado.getAutor());
                topicoExistente.setCurso(topicoActualizado.getCurso());
                topicoExistente.setAnio(topicoActualizado.getAnio());

                Topico topicoActualizadoEnDB = topicoRepository.save(topicoExistente);
                return ResponseEntity.ok(topicoActualizadoEnDB);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ya existe un tópico con el mismo título y mensaje.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        // Verificar si el tópico existe en la base de datos
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok("Tópico con ID " + id + " ha sido borrado correctamente.");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún tópico con el ID " + id);

        }
    }
}
