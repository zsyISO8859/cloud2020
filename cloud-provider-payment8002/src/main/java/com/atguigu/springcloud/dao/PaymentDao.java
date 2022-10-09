package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/25 16:39
 */

@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPayment(@Param("id")Long id);
}
