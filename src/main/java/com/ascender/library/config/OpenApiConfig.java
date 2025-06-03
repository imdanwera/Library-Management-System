package com.ascender.library.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library Management API",
                version = "1.0",
                description = "REST API for managing books, borrowers, and lending in a library system.",
                contact = @Contact(name = "Library API Support", email = "support@ascender.com"),
                license = @License(name = "Apache 2.0", url = "http://springdoc.org")
        ),
        servers = @Server(url = "http://localhost:8080", description = "Local server")
)
public class OpenApiConfig {
}
