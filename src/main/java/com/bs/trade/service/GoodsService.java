package com.bs.trade.service;

import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.UserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 物品相关操作接口
 * Created by wangyanan on 2016/4/7.
 */
public interface GoodsService extends IService<GoodsInfo> {


    /**
     * 根据条件分页查询（并根据指定属性排序）
     *
     * @param goodsInfo 查询条件
     * @param page      分页条件
     * @param rows      分页条件
     * @param sort      排序条件
     * @return
     */
    PageInfo<GoodsVo> selectGoodsInfo(GoodsInfo goodsInfo, int page, int rows, String sort);

    PageInfo<GoodsVo> selectGoodsInfo(GoodsInfo goodsInfo, int page, int rows);

    PageInfo<GoodsVo> selectAny(String  search, int page, int rows);


    PageInfo<GoodsVo> adminGoodsInfo(int page, int rows);

    List<GoodsInfo> selectByGoods(GoodsInfo goodsInfo, int page, int rows);

    public PageInfo<GoodsVo> selectMoney(GoodsInfo goodsInfo, int page, int rows, String sort);

    GoodsVo getGoodsInfo(GoodsVo goodsVo);

    GoodsVo adminGetGoodsInfo(GoodsVo goodsVo);



    int getListOne();

    PageInfo<GoodsVo> showAdd(UserInfo userInfo, int page, int rows);


}
