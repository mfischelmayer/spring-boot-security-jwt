package at.fischelmayer.securitydemo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping( "/admin" )
    public String users() {
        return "ok ok, you're an admin";
    }
}
