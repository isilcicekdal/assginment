package com.smartict.assginment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StationNotNullException extends RuntimeException{

    public StationNotNullException(String message) {
        super(message);
    }
}
