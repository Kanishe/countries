package com.aka.countries.exception;

public class NotFoundCountryByIdException extends RuntimeException {
    public NotFoundCountryByIdException() {

    }

    public NotFoundCountryByIdException(String message) {
        super(message);
    }
}
