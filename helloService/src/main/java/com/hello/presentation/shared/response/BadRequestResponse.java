package com.hello.presentation.shared.response;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestResponse {

    private String field;
    private String message;
}
