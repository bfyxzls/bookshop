package com.lind.bookshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.lind.bookshop.mapper")
@ServletComponentScan
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableFeignClients
public class BookshopApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookshopApplication.class, args);
  }

}
