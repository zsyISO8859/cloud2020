package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/1 10:15
 */
@Component
@FeignClient(value = "cloud-payment-com.atguigu.springcloud.service")
public interface PaymentFeignService {
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeOut();
}
