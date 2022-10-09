package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/15 19:13
 */

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        System.out.println("排队等待...");
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    @GetMapping("/testD")
    public String testD()
    {
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
        //暂停几秒钟线程
        log.info("testE 测试 异常比例");
        int i = 10/0;
        return "------testE";
    }

    @GetMapping("/testF")
    public String testF()
    {
        //暂停几秒钟线程
        log.info("testF 测试 异常比例");
        int i = 10/0;
        return "------testF";
    }

    @GetMapping("/hotKey")
    @SentinelResource(value = "hotKey",blockHandler = "dealHandler_testHotKey")
    public String hotKey(@RequestParam(value = "p1",required = false)String p1,
                         @RequestParam(value = "p2",required = false)String p2){
        //int u = 10/0;
        return "hotKey-----";
    }
    public String dealHandler_testHotKey(String p1, String p2, BlockException be){
        return "blockHandlerTest----";
    }
}
