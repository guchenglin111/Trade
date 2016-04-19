package com.bs.trade.service.impl;

import com.bs.trade.model.GoodsInfo;
import com.bs.trade.service.GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 物品相关操作实现
 * Created by wangyanan on 2016/4/7.
 */
@Service("goodsService")
public class GoodsServiceImpl extends BaseService<GoodsInfo> implements GoodsService{


    @Override
    public List<GoodsInfo> selectByGoods(GoodsInfo goodsInfo, int page, int rows) {
        Example example = new Example(GoodsInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(goodsInfo.getName())) {
            criteria.andLike("name", "%" +goodsInfo.getName() + "%");
        }
        if (StringUtil.isNotEmpty(goodsInfo.getRegion())) {
            criteria.andLike("region", "%" + goodsInfo.getRegion() + "%");
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }
}
