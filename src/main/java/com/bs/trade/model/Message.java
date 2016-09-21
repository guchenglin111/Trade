package com.bs.trade.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 推送信息实体
 * Created by wangyanan on 2016/5/4.
 */
@Table(name = "push_message")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 外键，消息发送者
     */
    private Integer sender;

    /**
     * 外键，消息接收者
     */
    private Integer receiver;

    /**
     * 外键，消息关联商品
     */
    private Integer goods;

    /**
     * 外键，消息类型
     */
    private Integer type;

    /**
     * 消息推送时间
     */
    @Column(name = "push_time")
    private Date pushTime;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息是否已读
     */
    @Column(name = "if_read")
    private Integer ifRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getIfRead() {
        return ifRead;
    }

    public void setIfRead(Integer ifRead) {
        this.ifRead = ifRead;
    }
}
