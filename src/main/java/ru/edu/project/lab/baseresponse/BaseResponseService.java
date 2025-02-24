package ru.edu.project.lab.baseresponse;

import org.springframework.stereotype.Service;
import ru.edu.project.lab.exception.PenzGtuException;

@Service
public class BaseResponseService {

    public <T> ResponseWrapper<T> wrapSuccessResponse(T body)  {
        return ResponseWrapper
                .<T>builder()
                .success(true)
                .body(body)
                .build();
    }

    public ResponseWrapper<?> wrapErrorResponse(PenzGtuException exception) {
        ErrorDto error = ErrorDto.builder()
                .code(exception.getType().name())
                .title(exception.getType().getTitle())
                .text(exception.getType().getText())
                .build();

        return ResponseWrapper
                .builder()
                .success(true)
                .error(error)
                .build();
    }

}
