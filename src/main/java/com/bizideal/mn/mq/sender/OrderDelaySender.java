package com.bizideal.mn.mq.sender;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:29
 * @version: 1.0
 * @Description:
 */
@Component
public class OrderDelaySender {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * @param message
     * @desc 即时发送
     */
    public void send(String queueName, String message) {
        jmsMessagingTemplate.convertAndSend(queueName, message);
    }

    /**
     *
     * @desc 延时发送
     */
    public void delaySend(String textMessage, String queueName, Long time) {
        //获取连接工厂
        ConnectionFactory connectionFactory = this.jmsMessagingTemplate.getConnectionFactory();
        try {
            //获取连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(textMessage);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 10 * 1000); //设置重复投递间隔（非必要，根据实际情况）
//            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 6); //重复投递次数（非必要，根据实际情况）
            //发送
            producer.send(message);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
