package kr.co.Dal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DalApplication {
    public static void main(String[] args) {
        SpringApplication.run(DalApplication.class, args);
    }
}