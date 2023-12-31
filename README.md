# OneHealth Application 
MicroServices 
OneHealth_ALL_API

**EUREKA-SERVER**
﻿https://serviceregistry-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/  

**API-GATEWAY**
https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/﻿

﻿
===============================================================================
**#SpringBoot SetUp**
Dependencies: 
https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.1.2&packaging=jar&jvmVersion=17&groupId=com.onehealth&artifactId=OneHealth-PatientCheckout&name=OneHealth-PatientCheckout&description=Demo%20project%20for%20Spring%20Boot&packageName=com.onehealth.patientcheckout&dependencies=web,data-jpa,postgresql,cloud-eureka,cloud-config-client,devtools


==============================================================================
The folder structure and naming the microservices for OneHealth application. Below is a suggested folder structure and microservices names for application:

1. Service Registry (Eureka Server):
   - Microservice Name: OneHealth-ServiceRegistry

2. Configuration Server:
   - Microservice Name: OneHealth-ConfigServer

3. API Gateway (Spring Cloud Gateway):
   - Microservice Name: OneHealth-APIGateway

4. User Registration and Login Service:
   - Microservice Name: OneHealth-User

5. Patient ManagementService:
   - Microservice Name: OneHealth-PatientManagement

6. Patient Document Management Service:
   - Microservice Name: OneHealth-PatientDocument

7. Pharmacy Login and Registration Service:
   - Microservice Name: OneHealth-Pharmacy

8. Pharmacy Inventory Management Service:
   - Microservice Name: OneHealth-PharmacyInventory

9. Pharmacy Cart Management Service:
   - Microservice Name: OneHealth-PharmacyCart

10. Pharmacy Patient Management Service:
    - Microservice Name: OneHealth-PharmacyPatient

11. OneHealth- LifeStyleAndHistory:
    - Microservice Name: OneHealth-LifeStyleAndHistory

12. Promocode and Coupons Service:
    - Microservice Name: OneHealth-Promocode

13. Promocode and Coupons Service:
    - Microservice Name: OneHealth-Promocode

Now, let's define the basic folder structure for each microservice:

For each Microservice (Example - OneHealth-User):
- onehealth-user
  - src
    - main
      - java
        - com.onehealth.com.user
          - config (for configuration classes)
          - controller (for RESTful API controllers)
          - model (for entity classes)
          - repository (for database access)
          - service (for business logic)
      - resources
        - application.properties (or application.yml for configuration)
    - test (contains test classes)

Repeat the above folder structure for all the other microservices, replacing "user" with the respective microservice names in each case.

Additionally, you'll need to create separate folders for different UI components, like "patient," "doctor," "lab," "pharmacy," and "admin." For example:

- onehealth-ui
  - src
    - main
      - resources
        - static (for CSS, JavaScript, etc.)
        - templates (for HTML templates)

Now, you can start building each microservice with its functionality. Each microservice should have its own set of RESTful APIs, service logic, database access, and any other necessary components.

As you proceed with the development, you can integrate the necessary dependencies like Eureka Server, Spring Cloud Gateway, Postgres database, authentication, authorization with JWT/OAuth2, Swagger UI, Sleuth, Zipkin server, Hystrix, etc., into each microservice as required.

Remember to register each microservice with the Service Registry (Eureka Server) and configure the API Gateway to route requests to the appropriate microservices.

Since creating and managing the entire application is a substantial task, I recommend breaking it down into smaller milestones and focusing on one microservice at a time, gradually expanding the functionality as you go.

Please note that the above folder structure and microservices names are just suggestions. You may modify them according to your specific project requirements and conventions.



===============================================================================
**DataBase**

CREATE DATABASE onehealth_user_db;
CREATE DATABASE onehealth_patientcheckout_db;

APPLICATION_NAME patient
POSTGRES_HOST  postgresql
POSTGRES_USER   postgres
POSTGRES_PASSWORD  ******
POSTGRES_DB onehealthdb
EUREKA_SERVER link/eureka




