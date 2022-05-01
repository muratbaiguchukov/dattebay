package kg.itacademy.testproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel {
    private String msg;
    private String exceptionClassName;
}