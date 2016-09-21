package com.bs.trade.controller;

import com.bs.trade.model.GoodsType;
import com.bs.trade.service.GoodsTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 物品类型操作控制类
 * Created by RI01796 on 2016/4/7.
 */
@Controller
public class GoodsTypeController {

    private String page_list = "index";


    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 商品类型列表
     */
    @RequestMapping(value = {"typelist"})
    public ModelAndView getList() {
        ModelAndView result = new ModelAndView(page_list);
        List<GoodsType> goodsTypeList = goodsTypeService.selectAll();
        result.addObject("goodType", goodsTypeList);
        return result;
    }
}
