package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.Collect;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.CollectService;
import com.bs.trade.service.GoodsService;
import com.bs.trade.session.SessionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 收藏
 * Created by wangyanan on 2016/5/13.
 */
@Controller
public class CollectController {
    @Autowired
    private CollectService collectService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加收藏
     */
    @RequestMapping(value = {"user/collect"})
    public ModelAndView collect(@RequestBody String good, HttpServletRequest request) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("login");
        }
        GoodsInfo goodsInfo = JSON.parseObject(good, GoodsInfo.class);
        Collect collect = new Collect();
        collect.setUser(curuser.getId());
        collect.setGoods(goodsInfo.getId());
        collect.setCollectTime(new Date());
        GoodsInfo goods = goodsService.selectByKey(goodsInfo);
        goods.setCollectCount(goods.getCollectCount() + 1);
        collectService.save(collect);
        goodsService.updateNotNull(goods);
        return new ModelAndView("shop");

    }


    /**
     * 取消收藏
     */
    @RequestMapping(value = {"user/cancleCollect"})
    public ModelAndView cancleCollect(@RequestBody String collects, HttpServletRequest request) {
        Collect collect = JSON.parseObject(collects, Collect.class);
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("login");
        }
        collect.setUser(curuser.getId());
        GoodsInfo goodsInfo = goodsService.selectByKey(collect.getGoods());
        goodsInfo.setCollectCount(goodsInfo.getCollectCount() - 1);
        collectService.delete(collect);
        goodsService.updateNotNull(goodsInfo);
        return new ModelAndView("personal");
    }

    /**
     * 显示收藏商品
     */
    @RequestMapping(value = {"user/collect/list"})
    public ModelAndView collectList(HttpServletRequest request, @RequestBody String user,
                                    @RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "5") int rows) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        Integer id = userInfo.getId();
        Integer sessionId = curuser.getId();
        if (id == -1 || id == sessionId) {
            PageInfo<GoodsVo> collectList = collectService.showCollect(curuser, page, rows);
            return new ModelAndView("personal", "list", collectList);
        } else {
            PageInfo<GoodsVo> collectList = collectService.showCollect(userInfo, page, rows);
            return new ModelAndView("personal", "list", collectList);
        }
    }

    /**
     * 该商品是否被收藏
     */
    @RequestMapping(value = {"user/ifCollect"})
    public ModelAndView ifCollect(@RequestBody String good, HttpServletRequest request) {
        GoodsVo goodsVo = JSON.parseObject(good, GoodsVo.class);
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        Collect collect = new Collect();
        collect.setGoods(goodsVo.getId());
        collect.setUser(userInfo.getId());
        boolean result = collectService.ifCollect(collect);
        return new ModelAndView("shop", "result", result);
    }
}
