package com.debuggeando_ideas.best_travel.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

//Cada vez que se ejecute mi aplicacion todas las clases con la anotacion @Configuration se ejecutaran primero
@Configuration
//Anotacion para la documentacion de la API
@OpenAPIDefinition(
        info = @Info( title = "Best Travel API", version = "1.0", description = "Documentation for endpoints of Best Travel API")
)
public class OpenApiConfig {



}
