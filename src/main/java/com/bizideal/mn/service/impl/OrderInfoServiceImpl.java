package com.bizideal.mn.service.impl;

import com.bizideal.mn.entity.OrderInfo;
import com.bizideal.mn.enums.Status;
import com.bizideal.mn.mq.sender.OrderDelaySender;
import com.bizideal.mn.repository.OrderInfoRepository;
import com.bizideal.mn.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:05
 * @version: 1.0
 * @Description:
 */
@Service
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo, Integer> implements OrderInfoService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;
    @Autowired
    private OrderDelaySender orderDelaySender;

    @Override
    public List<OrderInfo> selectExpiredOrderInfo(Date expireDate) {
        return orderInfoRepository.selectExpiredOrderInfo(expireDate);
    }

    @Override
    public Integer createOrder(OrderInfo orderInfo) {
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setStatus(Status.WAITING_PAY.name());
        orderInfo.setExpireTime(new Date(date.getTime() + 5 * 60 * 1000)); // 20秒
        orderInfoRepository.save(orderInfo);
        int id = orderInfo.getId();
        orderDelaySender.delaySend("" + id, "delayOrder", 5 * 60 * 1000L);
        return id;
    }
}
