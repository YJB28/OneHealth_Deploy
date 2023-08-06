package com.onehealth.apigateway.config;



//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RouteConfig {
//    
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//        		
//                .route("PatientUser", r -> r.path("/oneHealth-patientuser/**").uri("lb://OneHealth-PatientUser"))
//                .route("PatientManagement", r -> r.path("/patientProfile/**").uri("lb://OneHealth-PatientManagement"))
//                .route("LifeStyleAndHistory", r -> r.path("/lifeStyleAndHistory/**").uri("lb://OneHealth-LifeStyleAndHistory"))
//                .route("OneHealth-DoctorAppointment", r -> r.path("/doctorAppointment/**").uri("lb://OneHealth-Appointment"))
//                // Add more routes for other microservices as needed
//                .build();
//    }
//}

//===



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig {

    @Autowired
    private EurekaDiscoveryClient eurekaDiscoveryClient;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        Builder routes = builder.routes();

        // Get a list of all registered service names
        List<String> serviceNames = eurekaDiscoveryClient.getServices();

        // For each service, create a route predicate to forward requests to its instances
        for (String serviceName : serviceNames) {
            // Define the base route that includes the service name as part of the path
            String baseRoute = "/" + serviceName.toLowerCase();

            // Add routes for specific paths under the base route
            routes.route(serviceName + "-route", r -> r.path(baseRoute + "/**")
                    .filters(f -> f.stripPrefix(1)) // Strip the base route from the path
                    .uri("lb://" + serviceName));
        }

        // Add more routes or filters as needed

        return routes.build();
    }
}


