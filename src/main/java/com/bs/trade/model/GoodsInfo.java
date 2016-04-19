package com.bs.trade.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 物品信息
 * Created by wangyanan on 2016/4/7.
 */
@Table(name = "goods")
public class GoodsInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 物品名称
     */
    private String name;

    /**
     * 磨损级别
     */
    private String lever;

    /**
     * 外键，商品类别
     */
    private Integer type;

    /**
     * 外键，所有者
     */
    private Integer owner;

    /**
     * 外键，地区
     */
    private String region;

    /**
     * 原价
     */
    @Column(name = "original_cost")
    private double originalCost;

    /**
     * 押金
     */
    @Column(name = "current_price")
    private double currentPrice;

    /**
     * 交易金额
     */
    @Column(name = "trade_cost")
    private double tradeCost;

    /**
     * 借出时间
     */
    @Column(name = "out_time")
    private Date outTime;

    /**
     * 归还时间
     */
    @Column(name = "back_time")
    private Date backTime;

    /**
     * 特例，交易物品是现价时的现金级别
     */
    @Column(name = "price_lever")
    private Integer priceLever;

    /**
     * 物品状态
     */
    private Integer status;

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

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(double originalCost) {
        this.originalCost = originalCost;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getTradeCost() {
        return tradeCost;
    }

    public void setTradeCost(double tradeCost) {
        this.tradeCost = tradeCost;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Integer getPriceLever() {
        return priceLever;
    }

    public void setPriceLever(Integer priceLever) {
        this.priceLever = priceLever;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lever='" + lever + '\'' +
                ", type=" + type +
                ", owner=" + owner +
                ", region=" + region +
                ", originalCost=" + originalCost +
                ", currentPrice=" + currentPrice +
                ", tradeCost=" + tradeCost +
                ", outTime=" + outTime +
                ", backTime=" + backTime +
                ", priceLever=" + priceLever +
                ", status=" + status +
                '}';
    }
}
