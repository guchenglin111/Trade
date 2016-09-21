package com.bs.trade.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 物品收藏表VO
 * Created by wangyanan on 2016/5/3.
 */
@Table(name = "collect")
public class Collect {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 外键，物品
     */
    private Integer goods;

    /**
     * 外键，收藏人
     */
    private Integer user;

    /**
     * 收藏时间
     */
    @Column(name = "collect_time")
    private Date collectTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}

