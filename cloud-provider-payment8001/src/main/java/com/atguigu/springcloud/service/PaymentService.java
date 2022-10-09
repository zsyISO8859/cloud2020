package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/25 16:56
 */

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPayment(Long id);
}
