package at.fischelmayer.securitydemo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @GetMapping( "/secret" )
    public String users() {
        return "some very secret information ;-)";
    }
}
