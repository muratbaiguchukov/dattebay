package kg.itacademy.testproject.exceptions;

public class PasswordNotFoundException extends RuntimeException {
    public PasswordNotFoundException(String message) {super(message);}
}