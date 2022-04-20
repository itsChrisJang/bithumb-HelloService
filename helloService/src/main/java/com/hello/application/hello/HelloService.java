package com.hello.application.hello;

import com.hello.application.hello.response.HelloMessageResponse;
import com.hello.application.hello.response.HelloMessageResponseFactory;
import com.hello.infrastructure.webClient.WebClientService;
import com.hello.infrastructure.webClient.response.PersonJobResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HelloService {

    private final WebClientService webClientService;
    private final HelloMessageResponseFactory helloMessageResponseFactory;

    /**
     * 전달된 이름으로 Hello 메시지 보내기
     * @param name : 이름
     * @return Mono<HelloMessageResponse> : 작성된 message
     */
    public Mono<HelloMessageResponse> helloMessage(String name) {

        Mono<PersonJobResponse> personJobResponseMono = webClientService.callInfoServiceJob(name);

        return helloMessageResponseFactory.HelloMessageResponseBuilder(name, personJobResponseMono);
    }
}