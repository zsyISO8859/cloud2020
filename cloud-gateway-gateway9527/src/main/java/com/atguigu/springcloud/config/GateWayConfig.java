package com.atguigu.springcloud.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/6 10:22
 */

@Configuration
public class GateWayConfig {

    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_atguigu",
                x-> x.path("/guonei").uri(URI.create("http://news.baidu.com/guonei")))
                .build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_atguigu2",
                x-> x.path("/payment/discovery/**").uri(URI.create("http://localhost:8001/payment/discovery")))
                .build();
        return routes.build();
    }
}
