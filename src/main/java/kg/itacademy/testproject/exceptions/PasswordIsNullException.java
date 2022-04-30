package kg.itacademy.testproject.exceptions;

public class PasswordIsNullException extends RuntimeException {
    public PasswordIsNullException (String message){
        super ( message );
    }

}
