package com.bs.trade.service.impl;

import com.bs.trade.mapper.GoodsTypeMapper;
import com.bs.trade.model.GoodsType;
import com.bs.trade.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物品类型操作实现
 * Created by RI01796 on 2016/4/7.
 */
@Service("goodstypeService")
public class GoodsTypeServiceImpl extends BaseService<GoodsType> implements GoodsTypeService {

    @Autowired
    protected GoodsTypeMapper mapper;

    @Override
    public List<GoodsType> selectAll() {
        return mapper.selectAll();
    }
}
