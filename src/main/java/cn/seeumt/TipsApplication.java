package cn.seeumt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.seeumt.dao")
@EnableCaching
public class TipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TipsApplication.class, args);
    }

}
