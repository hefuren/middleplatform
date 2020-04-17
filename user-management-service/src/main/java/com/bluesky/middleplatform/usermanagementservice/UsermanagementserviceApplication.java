package com.bluesky.middleplatform.usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(basePackages = "com.bluesky.middleplatform.usermanagementservice")
@ComponentScan(basePackages={"com.bluesky.middleplatform"})
@SpringBootApplication
public class UsermanagementserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermanagementserviceApplication.class, args);



    }

}
