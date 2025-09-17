package com.crud.clinica.clinica.exception;


public class DniYaExisteException extends RuntimeException {
    public DniYaExisteException(String mensaje) {
        super(mensaje);
    }
}
