package com.lind.bookshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.lind.bookshop.mapper")
@ServletComponentScan
@SpringBootApplication
@EnableCaching
@EnableAsync
public class BookshopApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookshopApplication.class, args);
  }

}
