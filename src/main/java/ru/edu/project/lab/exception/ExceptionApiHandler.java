package ru.edu.project.lab.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.edu.project.lab.baseresponse.BaseResponseService;
import ru.edu.project.lab.baseresponse.ErrorType;
import ru.edu.project.lab.baseresponse.ResponseWrapper;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionApiHandler {
    private final BaseResponseService baseResponseService;

    @ExceptionHandler(Throwable.class)
    public ResponseWrapper<?> handleOtherException(Throwable t) {
        log.error("Got exception {}, message: {}",t.getClass(),t.getMessage());
        return baseResponseService.wrapErrorResponse(new PenzGtuException(ErrorType.COMMON_ERROR,t));
    }

    @ExceptionHandler(PenzGtuException.class)
    public ResponseWrapper<?> handlePenzGtuException(PenzGtuException exception) {
        log.error("Got PenzGtuException {}, message: {}",exception.getClass(), exception.getMessage());
        return baseResponseService.wrapErrorResponse(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseWrapper<?> handleValidationException(Exception e) {
        log.error("Got validation exception {}, message: {}",e.getClass(),e.getMessage());
        return baseResponseService.wrapErrorResponse(new PenzGtuException(ErrorType.CLIENT_ERROR,e));
    }
}
