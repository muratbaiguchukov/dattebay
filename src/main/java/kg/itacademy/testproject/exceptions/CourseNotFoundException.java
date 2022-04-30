package kg.itacademy.testproject.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException ( String message )
    {
        super ( message );
    }
}
