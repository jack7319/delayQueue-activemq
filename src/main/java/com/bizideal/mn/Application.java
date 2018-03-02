package com.bizideal.mn;

import com.bizideal.mn.config.SpringUtil;
import com.bizideal.mn.entity.OrderInfo;
import com.bizideal.mn.mq.sender.OrderDelaySender;
import com.bizideal.mn.service.OrderInfoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

@EnableTransactionManagement
@SpringBootApplication
public class Application {

    // activemq开启延迟投递功能，需要在activemq.xml文件 broker节点上添加schedulerSupport="true"属性
    // 否则延迟投递无效
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InterruptedException {
        SpringApplication.run(Application.class, args);
        OrderDelaySender orderDelaySender = SpringUtil.getBean(OrderDelaySender.class);
        final OrderInfoService orderInfoService = SpringUtil.getBean(OrderInfoService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30000; i++) {
                    orderInfoService.createOrder(new OrderInfo());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30000; i++) {
                    orderInfoService.createOrder(new OrderInfo());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30000; i++) {
                    orderInfoService.createOrder(new OrderInfo());
                }
            }
        }).start();
    }


}
