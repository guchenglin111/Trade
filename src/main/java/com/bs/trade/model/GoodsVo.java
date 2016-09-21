package com.bs.trade.model;

import java.util.Date;

/**
 * 商品展示信息
 * Created by wangyanan on 2016/4/23.
 */
public class GoodsVo {

    /**
     * 商品ID
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品所在地区
     */
    private String region;

    /**
     * 商品所有者ID
     */
    private Integer userId;

    /**
     * 商品拥有者名称
     */
    private String ownerName;

    /**
     * 商品类型ID
     */
    private Integer typeId;

    /**
     * 商品类型
     */
    private String typeName;

    /**
     * 商品图片
     */
    private String imageUrl;

    /**
     * 用户头像
     */
    private String userImage;

    /**
     * 磨损级别
     */
    private String lever;
    /**
     * 原价
     */
    private Integer originalCost;

    /**
     * 押金
     */
    private Integer currentPrice;

    /**
     * 交易金额
     */
    private Integer tradeCost;

    private String phone;

    private Integer outCount;
    /**
     * 商品细节描述
     */
    private String detail;

    /**
     * 发布时间
     */
    private Date pubTime;
    /**
     * 收藏Id
     */
    private Integer collectId;
    /**
     * 收藏时间
     */
    private Date collectTime;

    /**
     * 交易Id
     */
    private Integer tradeId;
    /**
     * 借出时间
     */
    private Date outTime;
    /**
     * 归还时间
     */
    private Date backTime;
    /**
     * 交易状态
     */
    private Integer tradeStatus;

    /**
     *
     *
     */
    private String lenderName;
    /**
     * 审核状态
     */
    private Integer checkStatus;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOutCount() {
        return outCount;
    }

    public void setOutCount(Integer outCount) {
        this.outCount = outCount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}
