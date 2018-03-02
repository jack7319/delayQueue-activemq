package com.bizideal.mn.schedule;

import com.bizideal.mn.entity.OrderInfo;
import com.bizideal.mn.enums.Status;
import com.bizideal.mn.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:07
 * @version: 1.0
 * @Description:
 */
@Component
public class OrderInfoSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderInfoSchedule.class);

    @Autowired
    private OrderInfoService orderInfoService;

    // 1分钟执行一次
    @Scheduled(cron = "0 0/1 * * * ?")
    public void dealExpireOrders() {
        List<OrderInfo> orderInfos = orderInfoService.selectExpiredOrderInfo(new Date());
        for (OrderInfo orderInfo : orderInfos) {
            orderInfo.setStatus(Status.EXPIRED.name());
            orderInfoService.update(orderInfo);
            LOGGER.info("订单ID为：" + orderInfo.getId() + "的订单失效");
        }
    }
}
