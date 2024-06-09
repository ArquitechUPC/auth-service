package org.arquitech.authservice;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@OpenAPIDefinition(
        info = @Info(
                title = "API Auth - GymRat",
                version = "1.0",
                description = "API to manage authentication part of the application called GymRat"
        )

)
@EnableDiscoveryClient
@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("RAILWAY_DB_HOST", dotenv.get("RAILWAY_DB_HOST"));
        System.setProperty("RAILWAY_DB_PORT", dotenv.get("RAILWAY_DB_PORT"));
        System.setProperty("RAILWAY_DB_USERNAME", dotenv.get("RAILWAY_DB_USERNAME"));
        System.setProperty("RAILWAY_DB_PASSWORD", dotenv.get("RAILWAY_DB_PASSWORD"));
        System.setProperty("RAILWAY_DB_NAME", dotenv.get("RAILWAY_DB_NAME"));

        SpringApplication.run(AuthServiceApplication.class, args);

    }

}
