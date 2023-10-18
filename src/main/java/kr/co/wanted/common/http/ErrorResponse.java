package kr.co.wanted.common.http;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse extends CommonResponse {
    private ErrorType type;
    private String description;
    private String message;

    public ErrorResponse(ErrorType type, String description) {
        this.type = type;
        this.description = description;
        this.message = type.getMessage();
        this.setStatus(type.getNumber());
    }

}

