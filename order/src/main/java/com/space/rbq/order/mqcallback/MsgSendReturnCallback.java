package com.space.rbq.order.mqcallback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author zhuzhe
 * @date 2018/5/25 15:54
 * @email 1529949535@qq.com
 */
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {

    /**
     * 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
     * 需要注意的是：该方法调用后，{@link MsgSendConfirmCallBack}中的confirm方法也会被调用，且ack = true
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("MsgSendReturnCallback [消息从交换机到队列失败]  message："+message);

        // TODO 消息从交换机到队列失败，重新发送

    }
}
