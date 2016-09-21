package com.bs.trade.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品评价
 * Created by RI01796 on 2016/5/13.
 */

@Table(name = "goods_evaluate")
public class Evaluate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 外键，被评价商品
     */
    private Integer goods;
    /**
     * 外键，评价用户
     */
    private Integer owner;
    /**
     * 评价级别
     */
    private Integer lever;
    /**
     * 评价时间
     */
    @Column(name = "evaluate_time")
    private Date evaluateTime;
    /**
     * 评价内容
     */
    private String content;

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

    public Integer getLever() {
        return lever;
    }

    public void setLever(Integer lever) {
        this.lever = lever;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
