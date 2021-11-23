package com.zxs.follow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxs
 * @create 2021-05-09 15:53
 */

@SpringBootApplication
@MapperScan("com.zxs.follow.mapper")
public class FollowApplication {
    public static void main(String[] args) {
        SpringApplication.run(FollowApplication.class,args);
    }
}
