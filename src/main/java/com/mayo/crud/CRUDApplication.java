package com.mayo.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Slf4j
@EnableWebMvc
public class CRUDApplication {

    public static void main(String[] args) {
        SpringApplication.run(CRUDApplication.class, args);
        log.info("CRUDApplication started...");
    }
}
