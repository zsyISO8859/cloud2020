package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/30 17:42
 */

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/consul")
    public String getInfo() {
        System.out.println(discoveryClient.getServices());
        return "springcloud with consul: " + serverPort + "\t\t" + UUID.randomUUID();
    }
}
