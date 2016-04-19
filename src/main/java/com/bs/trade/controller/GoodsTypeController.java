package com.bs.trade.controller;

import com.bs.trade.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 物品类型操作控制类
 * Created by RI01796 on 2016/4/7.
 */
@Controller
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;
}
