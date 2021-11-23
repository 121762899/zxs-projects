package com.zxs.restaurants;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxs
 * @create 2021-05-18 10:17
 */

@SpringBootApplication
@MapperScan("com.zxs.restaurants.mapper")
public class RestaurantApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class,args);
    }

}
