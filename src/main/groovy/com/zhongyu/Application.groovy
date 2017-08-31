package com.zhongyu

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication(scanBasePackages = ['com.zhongyu'])
@ServletComponentScan()
class Application {

    static void main(String[] args) {
        SpringApplication.run Application, args
    }

}
