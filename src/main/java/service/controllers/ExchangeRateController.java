package service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    @RequestMapping
    public String greeting() {
        return "Hello";
    }
}
