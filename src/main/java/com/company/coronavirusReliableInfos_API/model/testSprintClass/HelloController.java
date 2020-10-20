package com.company.coronavirusReliableInfos_API.model.testSprintClass;

import com.company.coronavirusReliableInfos_API.model.testSprintClass.HelloMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    // localhost:8085/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "<h1>Hello, world!</h1>";
    }

    // localhost:8085/helloWithName?name=Bartosz
    @RequestMapping(value = "/helloWithName", method = RequestMethod.GET)
    @ResponseBody
    public String helloWithName(HttpServletRequest httpServletRequest) {
        // get name parameter from request;
        // will be null if no parameter passed in
        String name = httpServletRequest.getParameter("name");

        // if null, then "world"
        if (name == null) {
            name = "World!";
        }

        return String.format("<h1>%s</h1>", HelloMessage.getMessage(name));
    }
}
