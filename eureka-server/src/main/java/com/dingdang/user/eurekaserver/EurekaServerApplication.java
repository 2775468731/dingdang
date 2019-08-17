package com.dingdang.user.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@SpringBootApplication
@EnableEurekaServer
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//自动注入数据源的配置（取消数据库配置）
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
