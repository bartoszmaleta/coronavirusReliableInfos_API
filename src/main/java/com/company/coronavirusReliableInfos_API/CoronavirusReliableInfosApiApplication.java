package com.company.coronavirusReliableInfos_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class CoronavirusReliableInfosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusReliableInfosApiApplication.class, args);
	}
	// localhost:8080/hello
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "<h1>Hello, world!</h1>";
	}
}


// TODO:
// - Article model and Scientist model relation