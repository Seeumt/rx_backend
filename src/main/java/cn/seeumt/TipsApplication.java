package cn.seeumt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.seeumt.dao")
public class TipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TipsApplication.class, args);
    }

}
