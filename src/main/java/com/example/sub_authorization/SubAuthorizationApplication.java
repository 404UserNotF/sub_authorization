package com.example.sub_authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SubAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubAuthorizationApplication.class, args);
    }

}
