package com.pharos.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/6/25 10:29 AM
 */
@EnableScheduling
@MapperScan("com.pharos.infrasturcture")
@SpringBootApplication(scanBasePackages = "com.pharos.*")
@RestController
public class PharosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharosApplication.class, args);
    }

    @GetMapping("/webState")
    public String webState() {
        return "SUCCESS";
    }
}
