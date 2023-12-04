package kr.co.Dal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
//        (exclude = {DataSourceAutoConfiguration.class})
public class DalApplication {
    public static void main(String[] args) {
        SpringApplication.run(DalApplication.class, args);
    }
}