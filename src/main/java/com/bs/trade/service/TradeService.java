package com.bs.trade.service;

import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.Trade;
import com.bs.trade.model.UserInfo;
import com.github.pagehelper.PageInfo;

/**
 * 交易
 * Created by wangyanan on 2016/5/13.
 */
public interface TradeService extends IService<Trade>{
    PageInfo<GoodsVo> showTrade(UserInfo userInfo,int page, int rows);
    PageInfo<GoodsVo> showBorrow(UserInfo userInfo,int page, int rows);
    PageInfo<GoodsVo> All(UserInfo userInfo,int page, int rows);

    boolean ifEvalute(Trade trade);

}
