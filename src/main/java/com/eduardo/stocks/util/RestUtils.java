package com.eduardo.stocks.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestUtils {
    public static <T> ResponseEntity<T> getResponseEntity(T object) {
        return getResponseEntity(object, HttpStatus.OK, HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<T> getResponseEntity(T object, HttpStatus goodStatus, HttpStatus badStatus) {
        if (object == null) {
            return new ResponseEntity<>(badStatus);
        }
        else  {
            return new ResponseEntity<>(object, goodStatus);
        }
    }
}
