package br.com.embiess83.core.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.embiess83.core.exception.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String entityNotFoundHandler(EntityNotFoundException ex) {
    return ex.getMessage();
  }
}
