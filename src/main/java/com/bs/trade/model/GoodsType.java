package com.bs.trade.model;

import javax.persistence.*;

/**
 * 物品类型
 * Created by RI01796 on 2016/4/7.
 */
@Table(name = "goods_type")
public class GoodsType {
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

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
