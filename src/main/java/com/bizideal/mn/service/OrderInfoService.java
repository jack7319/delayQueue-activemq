package com.bizideal.mn.service;

import com.bizideal.mn.entity.OrderInfo;

import java.util.Date;
import java.util.List;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:04
 * @version: 1.0
 * @Description:
 */
public interface OrderInfoService extends BaseService<OrderInfo, Integer> {

    List<OrderInfo> selectExpiredOrderInfo(Date expireDate);

    Integer createOrder(OrderInfo orderInfo);
}