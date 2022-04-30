package kg.itacademy.testproject.exceptions;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException (String message) {
        super(message);
    }
}
