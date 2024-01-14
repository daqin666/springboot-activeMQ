package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "spring.activemq.jms", name = "enable",havingValue = "true")
public class consumer {
    @JmsListener(destination = ActiveMQConfig.QUEUE_NAME, containerFactory = "queueListener")
    public void readActiveQueue(Person message) throws Exception {
        System.out.println(String.format("activeMq 使用 queue 模式接收到消息：%s", message.toString()));
    }

    @JmsListener(destination = ActiveMQConfig.TOPIC_NAME, containerFactory = "topicListener")
    public void readActiveTopic1(String message) throws Exception {
        System.out.println(String.format("activeMq1 使用 topic 模式接收到消息：%s", message));
    }

    @JmsListener(destination = ActiveMQConfig.TOPIC_NAME, containerFactory = "topicListener")
    public void readActiveTopic2(String message) throws Exception {
        System.out.println(String.format("activeMq2 使用 topic 模式接收到消息：%s", message));
    }
}
