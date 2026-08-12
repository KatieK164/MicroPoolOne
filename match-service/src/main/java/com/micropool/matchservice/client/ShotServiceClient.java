package com.micropool.matchservice.client;

import com.micropool.matchservice.model.ShotOutcome;
import com.micropool.matchservice.model.ShotResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class ShotServiceClient {

    private final RestClient restClient = RestClient.create("http://shot-service:8080");

    public ShotOutcome takeShot(int angle, int power, int spin) {
        try {
            ShotServiceResponse response = restClient.post()
                    .uri("/shots")
                    .body(new ShotServiceRequest(angle, power, spin))
                    .retrieve()
                    .body(ShotServiceResponse.class);
            return new ShotOutcome(ShotResult.valueOf(response.result()), response.resultCode());
        } catch (RestClientException ex) {
            throw new RuntimeException("SHOT_SERVICE_UNAVAILABLE", ex);
        }
    }

    private record ShotServiceRequest(int angle, int power, int spin) {}
    private record ShotServiceResponse(String result, int resultCode) {}
}
