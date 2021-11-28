package com.peopleflow.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProducerStarter {
    public static void main(String... args) {
        SpringApplication.run(ProducerStarter.class, args);
    }
}
