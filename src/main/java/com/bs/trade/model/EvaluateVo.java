package com.bs.trade.model;

import java.util.Date;

/**
 * Created by wyn on 2016/5/16.
 */
public class EvaluateVo {

    private Integer id;

    /**
     * 用户Id
     */
    private Integer owner;

    /**
     * 商品
     */
    private Integer goods;

    /**
     * 用户名称
     */
    private String userName;
    /**
     * 评价级别
     */
    private Integer lever;
    /**
     * 评价时间
     */
    private Date evaluateTime;
    /**
     * 评价内容
     */
    private String content;

    /**
     * 用户头像
     */
    private String userImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLever() {
        return lever;
    }

    public void setLever(Integer lever) {
        this.lever = lever;
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

    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
