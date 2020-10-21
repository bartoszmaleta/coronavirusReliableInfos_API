package com.company.coronavirusReliableInfos_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
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
}


// TODO:
// - Article model and Scientist model relation
// - how to fill db
// - relations between classes!
// - DTO serialzie @IgnoreJson
// - custom serialization