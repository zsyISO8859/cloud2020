package com.atguigu.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/6 15:27
 */

//自定义过滤器  必须传uname参数 不然不允许路由
@Component //必须加，必须加，必须加
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    //int HIGHEST_PRECEDENCE = -2147483648;
    //int LOWEST_PRECEDENCE = 2147483647;
    //优先级
    @Override
    public int getOrder() {
        return 0;
    }
}
