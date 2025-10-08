package com.thullyo.api_gateway_vehicle_establishment.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("vehicle_create", r -> r.path("/vehicle")
                        .and().method("POST")
                        .uri("http://localhost:8080/api/vehicles"))
                .route("vehicle_update", r -> r.path("/vehicle")
                        .and().method("PUT")
                        .uri("http://localhost:8080/api/vehicles"))
                .route("vehicle_getById", r -> r.path("/vehicle/{id}")
                        .and().method("GET")
                        .uri("http://localhost:8080/api/vehicles"))
                .route("vehicle_delete", r -> r.path("/vehicle/{id}")
                        .and().method("DELETE")
                        .uri("http://localhost:8080/api/vehicles"))
                .route("vehicle_assignDriver", r -> r.path("/vehicle/assign-driver")
                        .and().method("POST")
                        .uri("http://localhost:8080/api/vehicles"))
                .build();
    }
}
