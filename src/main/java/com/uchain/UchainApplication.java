package com.uchain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uchain.dao")
public class UchainApplication {

    public static void main(String[] args) {
        SpringApplication.run(UchainApplication.class, args);
    }

}
