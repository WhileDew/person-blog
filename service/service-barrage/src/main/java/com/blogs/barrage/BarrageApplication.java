package com.blogs.barrage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 21380
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.blogs"})
@MapperScan("com.blogs.barrage.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class BarrageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarrageApplication.class, args);
    }
}
