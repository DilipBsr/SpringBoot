package com.admiral.springBootExample1.demo.customEndpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class CustomEndpoint {
    @ReadOperation
    public String custom() {
        return "Custom actuator endpoint!";
    }
}