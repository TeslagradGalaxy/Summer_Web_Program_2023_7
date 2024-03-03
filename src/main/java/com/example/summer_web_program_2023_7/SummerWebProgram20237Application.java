package com.example.summer_web_program_2023_7;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.summer_web_program_2023_7.mapper")
public class SummerWebProgram20237Application {

    public static void main(String[] args) {
        SpringApplication.run(SummerWebProgram20237Application.class, args);
    }

}
