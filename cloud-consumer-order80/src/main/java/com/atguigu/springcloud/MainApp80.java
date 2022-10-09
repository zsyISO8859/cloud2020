package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/25 22:37
 */

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name="cloud-payment-com.atguigu.springcloud.service",configuration = MySelfRule.class)
public class MainApp80 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp80.class, args);
    }
}
