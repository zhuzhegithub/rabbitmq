package com.space.rbq.order.mqcallback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 消息发送到交换机确认机制
 * @author zhuzhe
 * @date 2018/5/25 15:53
 * @email 1529949535@qq.com
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    /**
     * 当消息发送到交换机（exchange）时，该方法被调用.
     * 1.如果消息没有到exchange,则 ack=false
     * 2.如果消息到达exchange,则 ack=true
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("MsgSendConfirmCallBack  , 回调correlationData:" + correlationData);
        if (ack) {
            System.out.println("消息发送到exchange成功");
            // TODO 删除 msgId 与 Message 的关系
        } else {
            System.err.println("消息发送到exchange失败");
            // TODO 消息发送到exchange失败 ， 重新发送
        }
    }
}
