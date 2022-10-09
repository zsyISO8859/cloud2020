package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/25 16:58
 */

@RestController
@Slf4j
public class PaymentController {
    @Resource
    DiscoveryClient discoveryClient;

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入数据结果=" + result);
        if (result > 0) {
            return new CommonResult(200, "创建成功,端口=" + serverPort, result);
        } else {
            return new CommonResult(444, "创建失败，端口=" + serverPort, null);
        }
    }

    @GetMapping("/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.getPayment(id);
        //log.info(payment.toString());
        if (payment != null) {
            return new CommonResult(200, "查询成功，端口=" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败，id=" + id + "系统不存在此记录！端口=" + serverPort, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("server="+service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getServiceId()+"\t"+
                        instance.getHost()+"\t"+
                        instance.getPort()+"\t"+
                        instance.getUri());
            }
        }
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        System.out.println("paymentFeignTimeOut from port"+serverPort);
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}


