package ru.edu.project.lab.exception;

import lombok.Getter;
import ru.edu.project.lab.baseresponse.ErrorType;


@Getter
public class PenzGtuException extends RuntimeException {
    private final ErrorType type;

    public PenzGtuException(ErrorType type, String message) {
        super(message);
        this.type = type;
    }
    public PenzGtuException(ErrorType type, String message, Throwable throwable) {
        super(message, throwable);
        this.type = type;
    }
    public PenzGtuException(ErrorType type, Throwable throwable) {
        super(throwable);
        this.type = type;
    }

}
