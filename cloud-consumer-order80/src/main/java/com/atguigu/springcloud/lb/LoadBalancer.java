package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/31 16:10
 */


public interface LoadBalancer {
    ServiceInstance serviceInstance(List<ServiceInstance> serviceInstances);
}
