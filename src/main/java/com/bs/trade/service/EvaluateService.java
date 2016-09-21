package com.bs.trade.service;

import com.bs.trade.model.Evaluate;
import com.bs.trade.model.EvaluateVo;
import com.bs.trade.model.GoodsInfo;
import com.github.pagehelper.PageInfo;

/**
 * 评价
 * Created by wangyanan on 2016/5/13.
 */
public interface EvaluateService extends IService<Evaluate>{

     PageInfo<EvaluateVo> getEvaluateByGoods(GoodsInfo goodsInfo,int page,int rows);
}
