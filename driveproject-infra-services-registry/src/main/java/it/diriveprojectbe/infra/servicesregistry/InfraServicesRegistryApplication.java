package it.diriveprojectbe.infra.servicesregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class InfraServicesRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraServicesRegistryApplication.class, args);
	}

}
