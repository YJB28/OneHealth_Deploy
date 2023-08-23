package com.onehealth.apigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

	private final Logger logger = LoggerFactory.getLogger(RouteConfig.class);

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		logger.info("...211...");

		return builder.routes()
				.route("PatientUser",
						r -> r.path("/patientUser/**").uri(
								"https://onehealthpatientuser-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))

				.route("PatientManagement", r -> r.path("/patientProfile/**")
						.uri("https://onehealthpatientmanagement-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))

				.route("LifeStyleAndHistory",
						r -> r.path("/lifeStyleAndHistory/**").uri(
								"https://lifestyleandhistory-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))

				.route("InventoryManagement",
						r -> r.path("/InventoryManagement/**").uri(
								"https://inventorymanagement-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))

				.route("OneHealth-feesservice",
						r -> r.path("/api/doctors/doctorfees/**")
								.uri("https://feesservice-madhavi-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-DoctorSchedule",
						r -> r.path("/api/doctors/schedule/**").uri(
								"https://doctorscheduleservice-madhavi-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-bankdetails",
						r -> r.path("/api/doctors/doctorbankdetails/**")
								.uri("https://bankdetails-madhavi-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-LabTest", r -> r.path("/api/lab-tests-order/**").uri(
						"https://lab-test-order-management-service-techbrutal11-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-/api/lab-carts", r -> r.path("/api/lab-carts/**").uri(
						"https://lab-test-cart-management-service-techbrutal11-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-/api/test", r -> r.path("/api/test/**").uri(
						"https://lab-test-management-service-techbrutal11-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-/api/labs", r -> r.path("/api/labs/**").uri(
						"https://labs-management-service-techbrutal11-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-/api/doctors/registration", r -> r.path("/api/doctors/addressprofileregistration/**")
						.uri("https://doctorregistrationprofile-madhavi-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))

				.route("OneHealth-/api/doctors/appointment",
						r -> r.path("/api/doctors/appointment/**").uri(
								"https://doctorappointment-madhavi-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/"))
				.route("OneHealth-PharmaManagement",
						r -> r.path("/pharmacies/**").uri(
								"https://pharmacy-management-service-git-yashwant01081998-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))
				.route("OneHealth-PharmaCartManagement",
						r -> r.path("/api/medCarts/**").uri(
								"https://pharmacy-cart-management-git-yashwant01081998-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))
				.route("OneHealth-PharmaOrderManagement",
						r -> r.path("/pharmacy-orders/**").uri(
								"https://onehealthpatientuser-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))

				// Add more routes for other microservices as needed
				.build();

	}
}

//===============
//package com.onehealth.apigateway.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//@Configuration
//public class RouteConfig {
//
//	private final Logger logger = LoggerFactory.getLogger(RouteConfig.class);
//
//	 @Bean
//	    public CorsWebFilter corsWebFilter() {
//	        final CorsConfiguration corsConfig = new CorsConfiguration();
//	        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
//	        corsConfig.setMaxAge(3600L);
//	        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Add OPTIONS
//	        corsConfig.addAllowedHeader("*");
//
//	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", corsConfig);
//
//	        return new CorsWebFilter(source);
//	    } 

// This route is defined using the @CrossOrigin annotation
//	@CrossOrigin("*")
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		logger.info("28");
//
//		return builder.routes()
//				.route("PatientUser",
//						r -> r.path("/patientUser/**").uri(
//								"http://localhost:8081/"))
//				.route("PatientManagement", r -> r.path("/patientProfile/**")
//						.uri("https://onehealthpatientmanagement-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))
//				.route("LifeStyleAndHistory",
//						r -> r.path("/lifeStyleAndHistory/**").uri(
//								"https://lifestyleandhistory-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))
//				.route("OneHealth-DoctorAppointment",
//						r -> r.path("/doctorAppointment/**")
//								.uri("https://doctorappointment-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/"))
//				// Add more routes for other microservices as needed
//				.build();
//	}
//}
