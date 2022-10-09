package com.atguigu.springcloud.service;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/1 16:44
 */


public interface PaymentService {
    public String paymentInfo_OK(Integer id);
    public String paymentInfo_TimeOut(Integer id);
}
