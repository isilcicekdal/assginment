package com.smartict.assginment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlateNotNullException extends RuntimeException{

    public PlateNotNullException(String message) {
        super(message);
    }
}
