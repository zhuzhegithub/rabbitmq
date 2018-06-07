package com.space.rbq.order.controller;

import com.google.gson.Gson;
import com.space.rbq.order.bean.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhuzhe
 * @date 2018/6/7 9:48
 * @email 1529949535@qq.com
 */
@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 保存order , 同时需要向store服务发送通知减库存
     * @param order
     * @return
     */
    @PostMapping("/save")
    public Order saveOrder(Order order){
        log.info(order.toString());
        Gson gson = new Gson();
        String json = gson.toJson(order);
        //  发送消息
        /**
         * 指定消息交换机  "first_exchange"
         * 指定队列key    "queue_one_key1"
         */
        rabbitTemplate.convertAndSend("first_exchange", "queue_one_key1",
                json, new CorrelationData(UUID.randomUUID().toString()));
        return order;
    }
}
