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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(RouteConfig.class);
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        Builder routes = builder.routes();

        // Get a list of all registered service names
        List<String> serviceNames = eurekaDiscoveryClient.getServices();
        logger.info("00000000000");
        logger.info("serviceNames name for service {} ", serviceNames);
        // For each service, create a route predicate to forward requests to its instances
        for (String serviceName : serviceNames) {
            // Define the base route that includes the service name as part of the path
            String baseRoute = "/" + serviceName.toLowerCase();

            // Add routes for specific paths under the base route
            routes.route(serviceName + "-route", r -> r.path(baseRoute + "/**")
                    .filters(f -> f.stripPrefix(1)) // Strip the base route from the path
                    .uri("lb://" + serviceName + "-" + getPodName(serviceName)));
        }

        // Add more routes or filters as needed

        return routes.build();
    }

    private String getPodName(String serviceName) {
        String podName = eurekaDiscoveryClient.getInstances(serviceName).get(0).getHost();
        logger.info("Pod name for service {} is {}", serviceName, podName);
        logger.info("111111111111111111");
        logger.info(podName.substring(podName.indexOf("-") + 1));
        logger.info("22222222222222222");
        return podName.substring(podName.indexOf("-") + 1);
    }
}
