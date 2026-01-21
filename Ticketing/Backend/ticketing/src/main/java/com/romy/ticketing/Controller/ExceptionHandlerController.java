package com.romy.ticketing.Controller;

import com.romy.ticketing.Exceptions.ExceptionsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionsDTO> notFound(Exception e){

        ExceptionsDTO eDTO = new ExceptionsDTO();

        eDTO.setFecha(new Date());

        eDTO.setError("No existe en la base de datos ese usuario");

        eDTO.setMessage(e.getMessage());

        eDTO.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.internalServerError().body(eDTO);

    }
}
