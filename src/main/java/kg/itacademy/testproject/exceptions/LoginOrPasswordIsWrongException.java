package kg.itacademy.testproject.exceptions;

import org.springframework.http.HttpStatus;

public class LoginOrPasswordIsWrongException extends RuntimeException {
    public LoginOrPasswordIsWrongException(String message, HttpStatus unauthorized) {super(message);}
}