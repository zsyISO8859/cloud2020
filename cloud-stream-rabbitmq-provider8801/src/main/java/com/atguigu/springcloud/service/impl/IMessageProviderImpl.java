package com.atguigu.springcloud.service.impl;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import com.atguigu.springcloud.service.IMessageProvider;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/9 11:07
 */

@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {
    @Resource
    private MessageChannel output;
    @Override
    public String send() {
        String str  = UUID.randomUUID().toString();
        Message<String> smb = MessageBuilder.withPayload(str).build();
        output.send(smb);
        System.out.println(str);
        return null;
    }
}
