package com.hello.infrastructure.webClient;

import com.hello.infrastructure.webClient.response.PersonJobResponse;
import com.hello.presentation.shared.response.BadRequestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
public class WebClientService {

    @Value("${msa.client.person.url}")
    private String personUrl;

    /**
     * 이름으로 직업 정보 조회하기
     * @param name : 이름
     * @return Mono<PersonJobResponse> : 직업 정보
     */
    public Mono<PersonJobResponse> callInfoServiceJob(String name) {

        return WebClient.create(personUrl).get()
                .uri("/info-service/job?name=" + name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // Response에서 바로 Body를 가지고 온다. --> bodyToMono(String.class) 형태로 바로 사용 가능 (bodyToFlux도 사용 가능)
                .onStatus(HttpStatus::is5xxServerError,
                        response -> Mono.error(new RuntimeException("서버 에러 발생"))
                )
                .onStatus(HttpStatus::is4xxClientError,
                        response -> response.bodyToMono(BadRequestResponse.class)
                                .flatMap(badRequestResponse -> Mono.error(new ServerWebInputException(badRequestResponse.toString())))
                )
                .bodyToMono(PersonJobResponse.class);
    }
}
