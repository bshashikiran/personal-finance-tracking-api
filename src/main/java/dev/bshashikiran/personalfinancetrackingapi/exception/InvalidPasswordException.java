package dev.bshashikiran.personalfinancetrackingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidPasswordException extends RuntimeException {
    
    public static final long serialVersionUID = -1L;

    public InvalidPasswordException (String message) {
        super(message);
    }
}
