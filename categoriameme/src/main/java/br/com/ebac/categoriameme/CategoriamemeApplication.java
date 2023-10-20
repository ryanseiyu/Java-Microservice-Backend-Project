package br.com.ebac.categoriameme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "br.com.ebac.categoriameme.repository")
public class CategoriamemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriamemeApplication.class, args);
	}

}
