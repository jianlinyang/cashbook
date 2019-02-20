package com.shu.cashbook;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.shu.cashbook.mapper")
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
public class CashbookApplication {
    public static void main(String[] args) {
        SpringApplication.run(CashbookApplication.class, args);
    }
}

