package com.alura.forohub.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    List<Topico> findByCursoAndAnio(String curso, int anio);

    boolean existsByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);
}