package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.CheckGoods;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.Message;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.CheckService;
import com.bs.trade.service.GoodsService;
import com.bs.trade.service.MessageService;
import com.bs.trade.service.UserInfoService;
import com.bs.trade.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 * Created by wyn on 2016/5/16.
 */
@Controller
public class CheckController {
    @Autowired
    private CheckService checkService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 审核商品
     * @param goodsCheck 待审核商品
     */
    @RequestMapping(value = {"admin/pass"})
    public ModelAndView pass (@RequestBody String goodsCheck){
        CheckGoods check = JSON.parseObject(goodsCheck, CheckGoods.class);
        //修改商品审核状态
        GoodsInfo goods = new GoodsInfo();
        goods.setId(check.getGoods());
        GoodsInfo goodsInfo = goodsService.selectByKey(goods);
        UserInfo userInfo = userInfoService.selectByKey(goodsInfo.getOwner());
        goodsInfo.setStatus(1);
        check.setTime(new Date());
        goodsService.updateNotNull(goodsInfo);
        //保存审核结果
        checkService.save(check);
        //推送消息
        Message message = new Message();
        message.setGoods(check.getGoods());
        message.setContent("亲爱的"+userInfo.getName()+"您发布的商品"+goodsInfo.getName()+"已经通过审核");
        message.setSender(1);
        message.setReceiver(goodsInfo.getOwner());
        message.setPushTime(new Date());
        message.setIfRead(0);//未读
        messageService.save(message);
        return new ModelAndView("adminItem");
    }


}
