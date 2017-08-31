package com.zhongyu

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ['com'])
class Application {

    static void main(String[] args) {
        SpringApplication.run Application, args
    }

}
