package com.jxust.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//开启缓存，默认ConcurrentMapCache
//引入guava则guava缓存（未生效，Springboot1.5.x版本可用，可能2.x做了更新）、redis则redisCache
public class SellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
    }

}
