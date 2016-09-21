package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.*;
import com.bs.trade.service.EvaluateService;
import com.bs.trade.service.TradeService;
import com.bs.trade.session.SessionService;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by wyn on 2016/5/16.
 */
@Controller
public class EvaluateController {

    private static final Logger LOGGER = Logger.getLogger(EvaluateController.class);

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TradeService tradeService;

    /**
     * 评论列表
     */
    @RequestMapping("goodsEvaluate")
    public ModelAndView getEvaluateByGoods(@RequestBody String goods,
                                           @RequestParam(required = false, defaultValue = "1") int page,
                                           @RequestParam(required = false, defaultValue = "5") int rows) {
        GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        ModelAndView result = new ModelAndView("shop");
        PageInfo<EvaluateVo> evaluateVos = evaluateService.getEvaluateByGoods(goodsInfo,page, rows);
        result.addObject("pageInfo", evaluateVos);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }

    /**
     * 评论
     */
    @RequestMapping("user/evaluate")
    public ModelAndView addEvaluate(HttpServletRequest request, @RequestBody String evaluate) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("shop", "i", -1);
        } else {
            EvaluateVo evaluateVo = JSON.parseObject(evaluate, EvaluateVo.class);
            Trade trade = new Trade();
            trade.setLender(curuser.getId());
            trade.setGoods(evaluateVo.getGoods());
            if (!tradeService.ifEvalute(trade)) {
                return new ModelAndView("shop", "i", -2);
            } else {
                Evaluate evaluate1 = new Evaluate();
                evaluate1.setContent(evaluateVo.getContent());
                evaluate1.setEvaluateTime(new Date());
                evaluate1.setGoods(evaluateVo.getGoods());
                evaluate1.setLever(evaluateVo.getLever());
                evaluate1.setOwner(curuser.getId());
                int i = evaluateService.save(evaluate1);
                return new ModelAndView("shop", "i", i);
            }
        }

    }

}
