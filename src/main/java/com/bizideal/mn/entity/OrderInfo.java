package com.bizideal.mn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/3 13:02
 * @version: 1.0
 * @Description:
 */
@Entity
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 651552223516421408L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String status;
    private Date createTime;
    private Date expireTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"expireTime\":\"")
                .append(expireTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
