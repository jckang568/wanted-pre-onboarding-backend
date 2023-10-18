package kr.co.wanted.common;


import jakarta.validation.ValidationException;
import kr.co.wanted.common.http.ErrorResponse;
import kr.co.wanted.common.http.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class CommonControllerAdvice {
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMessageNotReadableException.class})
    public ErrorResponse handleRequestError(Exception e) {
        log.error("HttpMediaTypeNotSupportedException | HttpMessageNotReadableException: ", e);
        return new ErrorResponse(ErrorType.REQUEST_ERROR, e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ErrorResponse handleValidationException(ValidationException e) {
        log.error("ValidationException: ", e);
        return new ErrorResponse(ErrorType.REQUEST_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException: ", e);
        return new ErrorResponse(ErrorType.REQUEST_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        String description = e.getBindingResult().getFieldErrors().get(e.getBindingResult().getFieldErrors().size() - 1).getDefaultMessage();
        return new ErrorResponse(ErrorType.REQUEST_ERROR, description);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error("Exception: ", e);
        return new ErrorResponse(ErrorType.UNKNOWN_ERROR, e.getMessage());
    }
}

