package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static java.util.TimeZone.getTimeZone;
import static java.util.TimeZone.setDefault;
import static service.infrastructure.configuration.Constants.DEFAULT_TIMEZONE;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        setDefault(getTimeZone(DEFAULT_TIMEZONE));
        SpringApplication.run(Application.class, args);
    }
}