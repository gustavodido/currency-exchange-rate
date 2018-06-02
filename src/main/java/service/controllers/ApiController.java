package service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController("api")
public class ApiController {

    @GetMapping("/latest")
    public String latest() {
        return "Hello";
    }

    @GetMapping("/history")
    public String history(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        return "Hello";
    }
}
