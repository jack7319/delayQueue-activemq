package com.bizideal.mn.mq.receiver;

import com.bizideal.mn.entity.OrderInfo;
import com.bizideal.mn.enums.Status;
import com.bizideal.mn.service.OrderInfoService;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:40
 * @version: 1.0
 * @Description:
 */
@Component
public class OrderDelayReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDelayReceiver.class);

    @Autowired
    private OrderInfoService orderInfoService;

    @JmsListener(destination = "delayOrder")
    public void getDelayOrder(String message) {
        LOGGER.info("接收到延迟消息：" + message);
        OrderInfo o = orderInfoService.findOne(Integer.valueOf(message));
        if (o != null && !Status.EXPIRED.name().equals(o.getStatus())) {
            o.setStatus(Status.EXPIRED.name());
            orderInfoService.update(o);
            LOGGER.info("订单：" + message + "设置为超时");
        }
    }
}
