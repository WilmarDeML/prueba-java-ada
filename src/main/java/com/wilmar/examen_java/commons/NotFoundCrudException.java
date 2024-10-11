package com.wilmar.examen_java.commons;


public class NotFoundCrudException extends RuntimeException {

    public NotFoundCrudException(String message) {
        super(message);
    }
}
