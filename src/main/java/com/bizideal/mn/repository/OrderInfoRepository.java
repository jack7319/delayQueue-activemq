package com.bizideal.mn.repository;

import com.bizideal.mn.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:04
 * @version: 1.0
 * @Description:
 */
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {

    @Query(value = "select o FROM OrderInfo o where o.expireTime < ?1")
    List<OrderInfo> selectExpiredOrderInfo(Date expireDate);
}