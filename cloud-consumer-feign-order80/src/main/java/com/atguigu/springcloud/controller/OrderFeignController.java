package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/1 10:19
 */

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id) {
        return paymentFeignService.getPayment(id);
    }

    @PostMapping("/consumer/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        return paymentFeignService.create(payment);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        int z = 10/0;
        return paymentFeignService.paymentFeignTimeOut();
    }
}
