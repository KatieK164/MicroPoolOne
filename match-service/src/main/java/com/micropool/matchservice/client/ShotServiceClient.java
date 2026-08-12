package com.micropool.matchservice.client;

import com.micropool.matchservice.model.ShotOutcome;

public interface ShotServiceClient {
    ShotOutcome takeShot(int angle, int power, int spin) throws InterruptedException;
}
