package com.craig.revolute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
@EnableEurekaClient
public class AccountDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountDatabaseApplication.class, args);
    }

}
