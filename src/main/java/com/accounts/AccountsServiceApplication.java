package com.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "customerAuditService")
@OpenAPIDefinition(
        info = @Info(
                title = "API Documentation for Accounts micro-service",
                description = "API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Swapnil Karav",
                        email = "sbk09@gmail.com"
                )
        )
)
public class AccountsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsServiceApplication.class, args);
    }

}
