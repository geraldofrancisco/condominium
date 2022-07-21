package com.p2p.condominium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
public class CondominiumApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondominiumApplication.class, args);
	}

}
