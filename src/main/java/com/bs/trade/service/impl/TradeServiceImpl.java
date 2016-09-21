package com.bs.trade.service.impl;

import com.bs.trade.mapper.TradeMapper;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.Trade;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.TradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交易
 * Created by wangyanan on 2016/5/13.
 */

@Service("tradeService")
public class TradeServiceImpl extends BaseService<Trade> implements TradeService {
   @Autowired
   private TradeMapper tradeMapper;


    @Override
    public PageInfo<GoodsVo> showTrade(UserInfo userInfo, int page, int rows) {
        Integer id = userInfo.getId();
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = tradeMapper.showTrade(id);
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public PageInfo<GoodsVo> showBorrow(UserInfo userInfo, int page, int rows) {
        Integer id = userInfo.getId();
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = tradeMapper.showBorrow(id);
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public PageInfo<GoodsVo> All(UserInfo userInfo, int page, int rows) {
        Integer id = userInfo.getId();
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = tradeMapper.showBorrow(id);
        List<GoodsVo> goodsVos2 = tradeMapper.showTrade(id);
        for (GoodsVo goodsVo : goodsVos2){
            goodsVos.add(goodsVo);
        }
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public boolean ifEvalute(Trade trade) {
        Integer lender = trade.getLender();
        Integer goods = trade.getGoods();
        Trade trade1 = tradeMapper.ifTrade(lender,goods);
         if (trade1 == null){
             return false;
         }

        return true;
    }
}
