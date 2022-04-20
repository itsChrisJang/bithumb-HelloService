package com.hello.infrastructure.webClient.response;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonJobResponse {

    private String job; // 직업 정보
}
