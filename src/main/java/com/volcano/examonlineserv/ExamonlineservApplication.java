package com.volcano.examonlineserv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.volcano.examonlineserv")
@ServletComponentScan("com.volcano.examonlineserv")
public class ExamonlineservApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamonlineservApplication.class, args);
    }

}
