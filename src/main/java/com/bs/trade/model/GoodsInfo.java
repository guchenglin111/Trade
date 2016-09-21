package com.bs.trade.model;

import javax.persistence.*;

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
    private Integer originalCost;

    /**
     * 押金
     */
    @Column(name = "current_price")
    private Integer currentPrice;

    /**
     * 交易金额
     */
    @Column(name = "trade_cost")
    private Integer tradeCost;

    /**
     * 特例，交易物品是现价时的现金级别
     */
    @Column(name = "price_lever")
    private Integer priceLever;

    /**
     * 物品状态
     */
    private Integer status;

    /**
     * 借出次数
     */
    @Column(name = "out_count")
    private Integer outCount;

    /**
     * 收藏次数
     */
    @Column(name = "collect_count")
    private Integer collectCount;

    /**
     * 搜索次数
     */
    @Column(name = "search_count")
    private Integer searchCount;
    /**
     * 商品细节描述
     */
    private String detail;

    @Column(name = "image_url")
    private String imageUrl;
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

    public Integer getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Integer originalCost) {
        this.originalCost = originalCost;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getTradeCost() {
        return tradeCost;
    }

    public void setTradeCost(Integer tradeCost) {
        this.tradeCost = tradeCost;
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

    public Integer getOutCount() {
        return outCount;
    }

    public void setOutCount(Integer outCount) {
        this.outCount = outCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
