package com.company.coronavirusReliableInfos_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories("com.company.coronavirusReliableInfos_API/repository")
public class CoronavirusReliableInfosApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoronavirusReliableInfosApiApplication.class, args);
    }

    // localhost:8085/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "<h1>Hello, world!</h1>";
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.company.coronavirusReliableInfos_API")).build();

    }
}


// TODO:
// - Article model and Scientist model relation
// - how to fill db
// - relations between classes!
// - DTO serialzie @IgnoreJson
// - custom serialization

//https://twitter.com/EricTopol
//https://twitter.com/jburnmurdoch

//    Udało mi się zrobić relacje ManyToMany, jednak jeszce nie działełem z tym JsonIgnore. Dzisiaj będę!
//
//        Powiedz mi jednak, czy ja powininem wykonać CRUDA dla wszystkich Encji?

// - why indexing in db ends as last index! but in different tables?
