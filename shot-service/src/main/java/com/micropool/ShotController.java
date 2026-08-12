package com.micropool;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShotController {

    private final ShotCalculator calculator;

    public ShotController(ShotCalculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping("/shots")
    public ShotResponse takeShot(@Valid @RequestBody ShotRequest request) {
        ShotResult result = calculator.calculate(request.getAngle(), request.getPower());
        int resultCode = calculator.getResultCode(request.getAngle(), request.getPower());
        return new ShotResponse(result.name(), resultCode);
    }
}
