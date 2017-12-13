package com.fang.passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SpringBootTestApplication {

  public static void main(String[] args) {
    // exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,
    // MybatisAutoConfiguration.class}
    SpringApplication.run(SpringBootTestApplication.class, args);
  }
}
