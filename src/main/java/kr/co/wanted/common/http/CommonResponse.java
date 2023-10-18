package kr.co.wanted.common.http;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class CommonResponse {
    private LocalDateTime time;
    private int status;

    public CommonResponse() {
        this.time = LocalDateTime.now();
        this.status = 200;
    }
}

