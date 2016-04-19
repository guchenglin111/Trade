package com.bs.trade.model;

/**
 * 模糊查询模型
 * Created by RI01796 on 2016/4/7.
 */
public class GoodsQueryModel {
    private GoodsInfo goodsInfo;

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public Integer getId() {return goodsInfo.getId();}

    public void setId(Integer id) {goodsInfo.setId(id);}

    public String getName() {return goodsInfo.getName();}

    public void setName(String name) {
        goodsInfo.setName(name);
    }

    public String getRegion() {
        return goodsInfo.getRegion();
    }

    public void setRegion(String region) {
        goodsInfo.setRegion(region);
    }
}
