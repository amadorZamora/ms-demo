package cl.acn.lab.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

import java.util.Collections;

/**
 * @author ACN-labs
 * Date: 09-08-20
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cl.acn.lab"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "ACN labs - ARCHETYPE REST API",
                "This is an example API for the UTFSM class",
                "API DEMO",
                "Terms of service (Editable)",
                new Contact("Amador Zamora Nuñez - Carlos Saez Del Canto", "www.accenture.com", "amador.zamora.nunez - c.saez.del.canto @accenture.com"),
                "License of API ", "API license URL  ", Collections.emptyList()
        );
    }

}
