package com.shu.cashbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CashbookApplication {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sayHi(){
        System.out.println("test");
        return "hello";
    }
    public static void main(String[] args) {
        SpringApplication.run(CashbookApplication.class, args);
    }

}

