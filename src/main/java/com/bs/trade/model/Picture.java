package com.bs.trade.model;

import javax.persistence.*;

/**
 * 商品图片信息
 * Created by wangyanan on 2016/5/13.
 */
@Table(name = "picture")
public class Picture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 外键，所属商品
     */
    private Integer goods;
    /**
     * 图片存储地址
     */
    private String print;


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

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }
}
