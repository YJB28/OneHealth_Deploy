package com.onehealth.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
//        		.route("default-path", r -> r.path("/").uri("lb://OneHealth-Default"))
                .route("PatientUser", r -> r.path("/patientUser/**").uri("lb://OneHealth-PatientUser"))
                .route("PatientManagement", r -> r.path("/patientProfile/**").uri("lb://OneHealth-PatientManagement"))
                .route("LifeStyleAndHistory", r -> r.path("/lifeStyleAndHistory/**").uri("lb://OneHealth-LifeStyleAndHistory"))
                .route("OneHealth-DoctorAppointment", r -> r.path("/doctorAppointment/**").uri("lb://OneHealth-Appointment"))
                // Add more routes for other microservices as needed
                .build();
    }
}