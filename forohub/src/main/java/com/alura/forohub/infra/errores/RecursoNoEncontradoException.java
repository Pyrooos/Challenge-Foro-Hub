package com.alura.forohub.infra.errores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String message) {
        super(message);
    }
}
