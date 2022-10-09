package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentFallbackServiceImpl;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/2 14:55
 */

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    PaymentHystrixService paymentHystrixService;
    @Resource
    PaymentFallbackServiceImpl paymentCircuitBreaker;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        //int z = 10/0;
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("****result: " + result);
        return result;
    }

    /**
     * 指定服务降级兜底的方法
     *
     * @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
     * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="1000")
     * })
     **/
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand //加了@DefaultProperties属性注解，并且没有写具体方法名字，就用统一全局的服务降级
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        //int x = 10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("****result: " + result);
        return result;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {

        return "这是8080 线程池:" + Thread.currentThread().getName() + "业务繁忙请稍等" + id + "\t";
    }

    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

    @GetMapping("/consumer/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentCircuitBreaker.paymentCircuitBreaker(id);
        log.info("****result: " + result);
        return result;
    }
}
