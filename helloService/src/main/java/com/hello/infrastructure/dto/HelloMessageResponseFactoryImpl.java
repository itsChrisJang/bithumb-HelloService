package com.hello.infrastructure.dto;


import com.hello.application.hello.response.HelloMessageResponse;
import com.hello.application.hello.response.HelloMessageResponseFactory;
import com.hello.infrastructure.webClient.response.PersonJobResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
public class HelloMessageResponseFactoryImpl implements HelloMessageResponseFactory {

    /**
     * HelloMessage 반환할 Response 구성
     * @param name : 이름
     * @param personJobResponseMono : 내부 통신을 통해 조회한 직업 정보
     * @return Mono<HelloMessageResponse> : 구성된 Response 반환
     */
    @Override
    public Mono<HelloMessageResponse> HelloMessageResponseBuilder(String name, Mono<PersonJobResponse> personJobResponseMono) {

        Mono<HelloMessageResponse> fallback = Mono.error(new ServerWebInputException("전달된 직업 정보가 없습니다."));

        return personJobResponseMono.flatMap(personJobResponse -> Mono.just(
                HelloMessageResponse.builder()
                        .to(name)
                        .job(personJobResponse.getJob()) // 직업 정보 반영
                        .message("hello " + name)
                        .build()
        )).switchIfEmpty(fallback);
    }
}