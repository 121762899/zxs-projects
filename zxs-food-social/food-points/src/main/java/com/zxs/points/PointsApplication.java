package com.zxs.points;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxs
 * @create 2021-05-15 16:30
 */

@MapperScan("com.zxs.points.mapper")
@SpringBootApplication
public class PointsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PointsApplication.class,args);
    }
}
