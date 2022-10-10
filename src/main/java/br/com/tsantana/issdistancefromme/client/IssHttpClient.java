package br.com.tsantana.issdistancefromme.client;

import br.com.tsantana.issdistancefromme.client.vo.IssGeolocation;
import br.com.tsantana.issdistancefromme.configuration.ApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class IssHttpClient {

    private final ApiProperties properties;

    public Mono<IssGeolocation> getIssGeolocation() {
        return WebClient.create()
                .get()
                .uri(properties.getOpenNotifyUrl())
                .retrieve()
                .bodyToMono(IssGeolocation.class);
    }
}
