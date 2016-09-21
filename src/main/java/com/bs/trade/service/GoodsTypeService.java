package com.bs.trade.service;

import com.bs.trade.model.GoodsType;

import java.util.List;

/**
 * 物品类型操作接口
 * Created by wangyanan on 2016/4/7.
 */
public interface GoodsTypeService extends IService<GoodsType> {

    List<GoodsType> selectAll();
}
