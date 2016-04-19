package com.bs.trade.service;

import com.bs.trade.model.GoodsInfo;

import java.util.List;

/**
 * 物品相关操作接口
 * Created by wangyanan on 2016/4/7.
 */
public interface GoodsService extends IService<GoodsInfo> {

    /**
     * 根据条件分页查询
     *
     * @param goodsInfo
     * @param page
     * @param rows
     * @return
     */
    List<GoodsInfo> selectByGoods(GoodsInfo goodsInfo, int page, int rows);
}
