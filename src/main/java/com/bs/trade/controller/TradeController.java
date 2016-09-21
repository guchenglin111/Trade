package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.Trade;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.EvaluateService;
import com.bs.trade.service.GoodsService;
import com.bs.trade.service.TradeService;
import com.bs.trade.service.UserInfoService;
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
 * 交易
 * Created by RI01796 on 2016/5/13.
 */
@Controller
public class TradeController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 借
     */
    @RequestMapping(value = {"user/borrow"})
    public ModelAndView borrowGoods(@RequestBody String goods, HttpServletRequest request) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("login");
        }
        GoodsVo goodsVo = JSON.parseObject(goods, GoodsVo.class);
        Integer cost = goodsVo.getCurrentPrice();
        UserInfo user = userInfoService.selectByKey(curuser);
        UserInfo temp = new UserInfo();
        temp.setId(1);
        UserInfo admin = userInfoService.selectByKey(temp);
        if (user.getAsset() < cost) {
            return new ModelAndView("shop", "result", 0);//账户余额不足
        }
        Trade trade = new Trade();
        trade.setGoods(goodsVo.getId());
        trade.setOwner(goodsVo.getUserId());
        trade.setCirculateStatus(0);
        trade.setLender(curuser.getId());
        trade.setLendTime(new Date());
        tradeService.save(trade);
        GoodsInfo gg = new GoodsInfo();
        gg.setId(goodsVo.getId());
        gg.setStatus(2);
        goodsService.updateNotNull(gg);
        user.setAsset(user.getAsset() - cost);
        userInfoService.updateNotNull(user);
        admin.setAsset(admin.getAsset() + cost);
        return new ModelAndView("shop", "result", 1);//交易

    }

    /**
     * 还
     */
    @RequestMapping(value = {"user/return"})
    public ModelAndView returnGoods(@RequestBody String parm, HttpServletRequest request) {
        Trade trade = JSON.parseObject(parm, Trade.class);
        UserInfo lender = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        trade.setBackTime(new Date());
        trade.setCirculateStatus(1);//1为完成一个周期
        UserInfo owner = userInfoService.selectByKey(trade.getOwner());
        GoodsInfo goodsInfo = goodsService.selectByKey(trade.getGoods());
        owner.setAsset(owner.getAsset() + goodsInfo.getTradeCost());
        lender.setAsset(lender.getAsset() + (goodsInfo.getCurrentPrice() - goodsInfo.getTradeCost()));
        UserInfo admin = userInfoService.selectByKey(1);
        admin.setAsset(admin.getAsset() - goodsInfo.getCurrentPrice());
        goodsInfo.setOutCount(goodsInfo.getOutCount() + 1);
        goodsInfo.setStatus(1);
        userInfoService.updateNotNull(owner);
        userInfoService.updateNotNull(lender);
        userInfoService.updateNotNull(admin);
        tradeService.updateNotNull(trade);
        goodsService.updateNotNull(goodsInfo);
        return new ModelAndView("personal");
    }

    /**
     * 借出列表
     * 包含交易状态
     */

    @RequestMapping(value = {"user/trade"})
    public ModelAndView tradeList(HttpServletRequest request, @RequestBody String user,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "5") int rows) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        Integer id = userInfo.getId();
        Integer sessionId = curuser.getId();
        if (id == -1 || id == sessionId) {
            PageInfo<GoodsVo> tradeList = tradeService.showTrade(curuser, page, rows);
            return new ModelAndView("tradeList", "list", tradeList);
        } else {
            PageInfo<GoodsVo> tradeList = tradeService.showTrade(userInfo, page, rows);
            return new ModelAndView("tradeList", "list", tradeList);
        }
    }

    /**
     * 借入列表
     * 包含交易状态
     */

    @RequestMapping(value = {"user/borrow/list"})
    public ModelAndView borrowList(HttpServletRequest request, @RequestBody String user,
                                   @RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "5") int rows) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        Integer id = userInfo.getId();
        Integer sessionId = curuser.getId();
        if (id == -1 || id == sessionId) {
            PageInfo<GoodsVo> borrowList = tradeService.showBorrow(curuser, page, rows);
            return new ModelAndView("personal", "list", borrowList);
        } else {
            PageInfo<GoodsVo> borrowList = tradeService.showBorrow(userInfo, page, rows);
            return new ModelAndView("personal", "list", borrowList);
        }
    }

    /**
     * 交易总列表
     */
    @RequestMapping(value = {"user/trade/list"})
    public ModelAndView tradeAllList(HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "5") int rows) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        PageInfo<GoodsVo> allList = tradeService.All(curuser, page, rows);
        return new ModelAndView("personal", "list", allList);
    }

}
