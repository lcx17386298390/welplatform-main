package com.hgnuacm.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("com.hgnuacm.wx.mapper")
public class WelplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(WelplatformApplication.class, args);
    }

}
