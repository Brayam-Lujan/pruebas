package com.supermercado.apigrocerystore.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@ControllerAdvice
@OpenAPIDefinition(
    info = @Info(
        title = "Api Tenis",
        version = "1.0",
        description = "Api para la venta y generacion de productos definidos llamados Tenis"
    ),
    tags = {
        @Tag(
            name = "Controlador Base",
            description = "Controlador base para la ApiTenis"
        )
    }
)
public class ApiBaseController {
    
}
