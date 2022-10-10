package br.com.tsantana.issdistancefromme.client;

import br.com.tsantana.issdistancefromme.client.vo.UserGeoLocation;
import br.com.tsantana.issdistancefromme.configuration.ApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeoJsClient {

    private final ApiProperties properties;

    public Mono<UserGeoLocation> getIssGeolocation(String clientIP) {
        return WebClient.create()
                .get()
                .uri(properties.getGeoJsUrl() + clientIP + ".json")
                .retrieve()
                .bodyToMono(UserGeoLocation.class);
    }
}
