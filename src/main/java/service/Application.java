package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.TimeZone.getTimeZone;
import static java.util.TimeZone.setDefault;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        setDefault(getTimeZone("UTC"));
        SpringApplication.run(Application.class, args);
    }
}