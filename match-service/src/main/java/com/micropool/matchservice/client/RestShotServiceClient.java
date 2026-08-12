package com.micropool.matchservice.client;

import com.micropool.matchservice.model.ShotOutcome;
import com.micropool.matchservice.model.ShotResult;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Duration;

@Component
public class RestShotServiceClient implements ShotServiceClient {

    private static final int MAX_ATTEMPTS = 3;
    private static final long RETRY_DELAY_MS = 200;

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://shot-service:8080")
            .requestFactory(ClientHttpRequestFactories.get(
                    ClientHttpRequestFactorySettings.DEFAULTS
                            .withConnectTimeout(Duration.ofSeconds(2))
                            .withReadTimeout(Duration.ofSeconds(3))))
            .build();

    @Override
    public ShotOutcome takeShot(int angle, int power, int spin) throws InterruptedException {
        RestClientException lastError = null;
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                ShotServiceResponse response = restClient.post()
                        .uri("/shots")
                        .body(new ShotServiceRequest(angle, power, spin))
                        .retrieve()
                        .body(ShotServiceResponse.class);
                return new ShotOutcome(ShotResult.valueOf(response.result()), response.resultCode());
            } catch (RestClientException ex) {
                lastError = ex;
                if (attempt < MAX_ATTEMPTS) sleep(RETRY_DELAY_MS);
            }
        }
        throw new ShotServiceUnavailableException("Could not resolve shot outcome, try again", lastError);
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
    }

    private record ShotServiceRequest(int angle, int power, int spin) {}
    private record ShotServiceResponse(String result, int resultCode) {}
}
