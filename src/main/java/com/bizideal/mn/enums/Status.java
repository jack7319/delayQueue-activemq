package com.bizideal.mn.enums;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:20
 * @version: 1.0
 * @Description:
 */
public enum Status {

    WAITING_PAY("等待支付"),
    EXPIRED("支付超时，订单无效"),
    COMPLETED("订单完成");

    private String name;

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
