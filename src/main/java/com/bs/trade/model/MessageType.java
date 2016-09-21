package com.bs.trade.model;

import javax.persistence.*;

/**
 * 消息类型
 * Created by wangyanan on 2016/5/13.
 */
@Table(name = "push_message")
public class MessageType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
