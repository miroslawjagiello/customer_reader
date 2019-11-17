package io.jagiello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CustomerReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerReaderApplication.class, args);
    }

}
