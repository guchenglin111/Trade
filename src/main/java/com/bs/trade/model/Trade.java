package com.bs.trade.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 交易
 * Created by wangyanan on 2016/5/13.
 */
@Table(name = "circulate_info")
public class Trade {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 外键，交易物品
     */
    private Integer goods;
    /**
     * 借出时间
     */
    @Column(name = "lend_time")
    private Date lendTime;
    /**
     * 归还时间
     */
    @Column(name = "back_time")
    private Date backTime;
    /**
     * 交易状态
     */
    @Column(name = "circulate_status")
    private Integer circulateStatus;//0借出，1完成交易
    /**
     * 商品所有者
     */
    private Integer owner;
    /**
     * 商品租赁者
     */
    private Integer lender;

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

    public Date getLendTime() {
        return lendTime;
    }

    public void setLendTime(Date lendTime) {
        this.lendTime = lendTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Integer getLender() {
        return lender;
    }

    public void setLender(Integer lender) {
        this.lender = lender;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getCirculateStatus() {
        return circulateStatus;
    }

    public void setCirculateStatus(Integer circulateStatus) {
        this.circulateStatus = circulateStatus;
    }
}
