package com.test.TestServer.exception;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    /*{
        "timestamp": "2025-04-25T02:24:23.010+00:00",
            "status": 500,
            "error": "Internal Server Error",
            "path": "/pets/1/2"
    }*/

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
