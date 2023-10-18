package kr.co.wanted.common.http;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class Error {
    private final String message;
    private final HttpStatus status;

    @Builder
    public Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static Error empty() {
        return Error.builder().build();
    }

    public static Error occurred(String message, HttpStatus httpStatus) {
        return Error.builder()
                .message(message)
                .status(httpStatus)
                .build();
    }
}

