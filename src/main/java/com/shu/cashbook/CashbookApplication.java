package com.shu.cashbook;


import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.shu.cashbook.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CashbookApplication {
    public static void main(String[] args) {
        SpringApplication.run(CashbookApplication.class, args);
    }
}

