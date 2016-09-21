package com.bs.trade.mapper;

import com.bs.trade.model.Evaluate;
import com.bs.trade.model.EvaluateVo;
import com.bs.trade.util.AllMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评价
 * Created by wangyanan on 2016/5/13.
 */
public interface EvaluateMapper extends AllMapper<Evaluate> {

    List<EvaluateVo> getEvaluateByGoods(@Param("goods") Integer goods);
}
