package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    /**
     * 发送消息
     * @param bean
     */
    public void sendQueue(Person bean) {
        System.out.println(String.format("activeMq 使用 queue 模式发送消息：%s", bean.toString()));
        sendMessage(queue, bean);
    }

    public void sendTopic(String message){
        System.out.println(String.format("activeMq 使用 topic 模式发送消息：%s", message));
        sendMessage(topic, message);
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    private void sendMessage(Destination destination, String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    private void sendMessage(Destination destination, Person bean){
        System.out.println("activeMq 发送自定义Bean");
        jmsMessagingTemplate.convertAndSend(destination, bean);
    }
}
