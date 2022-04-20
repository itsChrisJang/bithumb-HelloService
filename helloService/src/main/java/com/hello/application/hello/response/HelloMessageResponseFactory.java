package com.hello.application.hello.response;


import com.hello.infrastructure.webClient.response.PersonJobResponse;
import reactor.core.publisher.Mono;

public interface HelloMessageResponseFactory {

    /**
     * HelloMessage 반환할 Response 구성
     * @param name : 이름
     * @param personJobResponseMono : 내부 통신을 통해 조회한 직업 정보
     * @return Mono<HelloMessageResponse> : 구성된 Response 반환
     */
    Mono<HelloMessageResponse> HelloMessageResponseBuilder(String name, Mono<PersonJobResponse> personJobResponseMono);
}