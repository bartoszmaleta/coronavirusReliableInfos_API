package com.company.coronavirusReliableInfos_API;

import com.company.coronavirusReliableInfos_API.controller.ScientistController;
import com.company.coronavirusReliableInfos_API.model.Scientist;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
//@ComponentScan("com.company.coronavirusReliableInfos_API")
@EnableJpaRepositories("com.company.coronavirusReliableInfos_API/repository")
public class CoronavirusReliableInfosApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoronavirusReliableInfosApiApplication.class, args);
//        ScientistController scientistController = new ScientistController();
//        scientistController.createScientist(new Scientist("John", "Smith", "john.smith.com", "Nowhere", 3));
    }

    // localhost:8080/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "<h1>Hello, world!</h1>";
    }
}


// TODO:
// - Article model and Scientist model relation
// - how to fill db
// - relations between classes!