package com.example.crm.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI crmOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRM Backend API")
                        .description("API documentation for the CRM Platform")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("youremail@domain.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
