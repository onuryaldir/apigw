package com.lokal.apigw.config;

import com.lokal.apigw.config.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("routems", r -> r.path("/api/routes/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8180"))

                .route("authms", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8195"))

                .route("explorems", r -> r.path("/api/explore/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8085"))

                .route("countryms", r -> r.path("/api/country/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8175"))
                .build();



    }
}
