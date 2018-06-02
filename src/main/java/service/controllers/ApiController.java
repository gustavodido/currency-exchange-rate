package service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class ApiController {

    @RequestMapping
    public String greeting() {
        return "Hello";
    }
}
