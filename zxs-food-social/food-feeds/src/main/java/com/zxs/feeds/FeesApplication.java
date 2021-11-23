package com.zxs.feeds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxs
 * @create 2021-05-11 17:52
 */

@SpringBootApplication
@MapperScan("com.zxs.feeds.mapper")
public class FeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeesApplication.class,args);
    }
}
