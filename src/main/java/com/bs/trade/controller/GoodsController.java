package com.bs.trade.controller;

import com.bs.trade.model.GoodsInfo;
import com.bs.trade.service.GoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 物品相关控制类
 * Created by wangyanan on 2016/4/7.
 */
@Controller
public class GoodsController {
    private String page_list = "index";

    private String redirect_list = "redirect:list";

   @Autowired
   private GoodsService goodsService;

    @RequestMapping(value = {"list", "index", "index.html", ""})
    public ModelAndView getList(GoodsInfo goods,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int rows) {
        ModelAndView result = new ModelAndView(page_list);
        List<GoodsInfo> goodsInfoList = goodsService.selectByGoods(goods,page,rows);
        result.addObject("pageInfo", new PageInfo<GoodsInfo>(goodsInfoList));
        result.addObject("queryParam", goods);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(GoodsInfo goods) {
        ModelAndView result = new ModelAndView();
        if (goods.getId() != null) {
            goods = goodsService.selectByKey(goods.getId());
        }
        result.addObject("goods", goods);
        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(GoodsInfo goods) {
        ModelAndView result = new ModelAndView(redirect_list);
        if (goods.getId() != null) {
            goodsService.updateAll(goods);
        } else {
            goodsService.save(goods);
        }
        return result;
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        goodsService.delete(id);
        return redirect_list;
    }


}
