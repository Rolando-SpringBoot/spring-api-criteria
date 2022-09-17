package com.enagas.msc.producto.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.enagas.arch.core.exception.handler.RestResponseEntityExceptionHandler;

/**
 * The application exception handler class to override o extend the core
 * exception handler.
 */
@ControllerAdvice
public class ApplicationResponseEntityExceptionHandler extends RestResponseEntityExceptionHandler{

}
