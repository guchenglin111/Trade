package com.bs.trade.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品审核
 * Created by wangyanan on 2016/5/13.
 */
@Table(name = "goods_check")
public class CheckGoods {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 外键，审核商品
     */
    private Integer goods;
    /**
     * 审核时间
     */
    private Date time;
    /**
     * 审核结果
     */
    private Integer result;
    /**
     * 审核未通过原因
     */
    private String reason;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
