// AppDocumentationConfigureAdapter Class

package dev.khetexpert.inc.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.Collections;

@Configuration
@OpenAPIDefinition
public class AppDocumentationConfigureAdapter {

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI().info(new Info().title("KhetExpert backend APIs Documentation").description("This Documentation will help us to implement backend services in KhetExpert Application.").version("2.1.7")).security(Collections.singletonList(new SecurityRequirement().addList("dev.khetexpert.inc"))).components(new Components().addSecuritySchemes("dev.khetexpert.inc", new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name(HttpHeaders.AUTHORIZATION)));
    }

}