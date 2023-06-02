package at.fischelmayer.securitydemo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping( "/hello" )
    public String greeting() {
        return "Hello, World!";
    }
}
