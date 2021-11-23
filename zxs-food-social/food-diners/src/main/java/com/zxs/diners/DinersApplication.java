package com.zxs.diners;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxs
 * @create 2020-11-21 9:57
 */

@SpringBootApplication
@MapperScan("com.zxs.diners.mapper")
public class DinersApplication {
    public static void main(String[] args) {
        SpringApplication.run(DinersApplication.class, args);
    }
}
