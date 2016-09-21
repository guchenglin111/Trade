package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.Picture;
import com.bs.trade.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wyn on 2016/5/16.
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;


    /**
     * 根据商品获得图片
     */
    @RequestMapping("goodsImage")
    public ModelAndView getPictureByGoods(@RequestBody String goods) {
        GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        Picture picture = new Picture();
        picture.setGoods(goodsInfo.getId());
        List<Picture> pictures = pictureService.query(picture);
        return new ModelAndView("shop", "pictures", pictures);

    }
}
