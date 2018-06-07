package com.space.rbq.store.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.space.rbq.store.bean.Order;
import com.space.rbq.store.service.StoreService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 负责接收处理订单服务发送的消息
 * @author zhuzhe
 * @date 2018/6/7 10:09
 * @email 1529949535@qq.com
 */
@Component
public class OrderConsumer {

    @Autowired
    private StoreService storeService;

    /*对列名称*/
    public final String QUEUE_NAME1 = "first-queue";

    /**
     * queues  指定从哪个队列（queue）订阅消息
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {QUEUE_NAME1})
    public void handleMessage(Message message,Channel channel) throws IOException {
        try {
            // 处理消息
            System.out.println("OrderConsumer {} handleMessage :"+message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            // 执行减库存操作
            storeService.update(new Gson().fromJson(new String(message.getBody()),Order.class));
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }
}
