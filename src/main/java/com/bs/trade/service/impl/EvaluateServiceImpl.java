package com.bs.trade.service.impl;

import com.bs.trade.mapper.EvaluateMapper;
import com.bs.trade.model.Evaluate;
import com.bs.trade.model.EvaluateVo;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.service.EvaluateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评价
 * Created by wangyanan on 2016/5/13.
 */
@Service("evaluateService")
public class EvaluateServiceImpl extends BaseService<Evaluate>  implements EvaluateService{
    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public PageInfo<EvaluateVo> getEvaluateByGoods(GoodsInfo goodsInfo,int page,int rows) {
        Integer goods = goodsInfo.getId();
        PageHelper.startPage(page, rows);
        List<EvaluateVo> evaluateVos = evaluateMapper.getEvaluateByGoods(goods);
        return new PageInfo<EvaluateVo>(evaluateVos);
    }
}
