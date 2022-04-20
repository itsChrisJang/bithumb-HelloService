package com.hello.application.hello.response;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessageResponse {

    private String to;
    private String job;
    private String message;
}
