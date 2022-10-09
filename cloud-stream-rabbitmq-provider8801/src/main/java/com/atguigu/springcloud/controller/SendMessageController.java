package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.springcloud.service.IMessageProvider;

import javax.annotation.Resource;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/9 11:16
 */

@RestController
@Slf4j
public class SendMessageController {
    @Resource
    public IMessageProvider iMessageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return iMessageProvider.send();
    }
}
