package com.test.TestServer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String timestamp;
    private Long status;
    private String errorMessage;
    private String path;
}
