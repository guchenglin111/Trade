package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.Focus;
import com.bs.trade.model.Message;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.ForcusService;
import com.bs.trade.service.MessageService;
import com.bs.trade.service.UserInfoService;
import com.bs.trade.session.SessionService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wyn on 2016/5/16.
 */
@Controller
public class ForcusController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ForcusService forcusService;

    @Autowired
    private MessageService messageService;
    /**
     * 关注
     */
    @RequestMapping("user/focus")
    public ModelAndView forcus(@RequestBody String forcus,HttpServletRequest request){
        Focus focus = JSON.parseObject(forcus, Focus.class);
        UserInfo userInfo = (UserInfo)sessionService.getAttribute(request,Constants.User_SESSION);
        if (userInfo == null){
            return new ModelAndView("login");
        }
        //关注成功
        focus.setThisId(userInfo.getId());
        forcusService.save(focus);

        //当前用户好友数+1
        userInfo.setFriendCount(userInfo.getFriendCount()+1);
        userInfoService.updateNotNull(userInfo);

        //被关注用户好友数+1
        UserInfo otherUser = userInfoService.selectByKey(focus.getFriendId());
        otherUser.setFansCount(otherUser.getFansCount()+1);

        //推送消息
        Message  message = new Message();
        message.setSender(1);
        message.setReceiver(focus.getFriendId());
        message.setContent("亲爱的 "+otherUser.getName()+" 用户 "+userInfo.getName()+" 关注了您");
        messageService.save(message);

        return new ModelAndView("personal");
    }
    /**
     * 取消关注
     */
    @RequestMapping("user/cancleFocus")
    public ModelAndView cancleForcus(@RequestBody String forcus,HttpServletRequest request){
        Focus focus = JSON.parseObject(forcus, Focus.class);
        UserInfo userInfo = (UserInfo)sessionService.getAttribute(request,Constants.User_SESSION);
        focus.setThisId(userInfo.getId());
        forcusService.delete(focus);

        //当前用户好友数-1
        userInfo.setFriendCount(userInfo.getFriendCount()-1);
        userInfoService.updateNotNull(userInfo);

        //被关注用户好友数-1
        UserInfo otherUser = userInfoService.selectByKey(focus.getFriendId());
        otherUser.setFansCount(otherUser.getFansCount()-1);

        return new ModelAndView("personal");
    }
    /**
     * 好友列表
     */
    @RequestMapping("user/friend")
    public ModelAndView getFriend(@RequestBody String user,HttpServletRequest request){
        UserInfo otherUser = JSON.parseObject(user, UserInfo.class);
        List<UserInfo> userInfos = Lists.newArrayList();
        Integer id  = otherUser.getId();
        if (id==-1) {
            UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
            userInfos = forcusService.getFriend(userInfo);
        }
        else {
            userInfos = forcusService.getFriend(otherUser);
        }
        return new ModelAndView("personal","userInfos",userInfos);
    }

    /**
     * 粉丝列表
     */
    @RequestMapping("user/fans")
    public ModelAndView getFans(@RequestBody String user,HttpServletRequest request){
        UserInfo otherUser = JSON.parseObject(user, UserInfo.class);
        List<UserInfo> userInfos = Lists.newArrayList();
        Integer id  = otherUser.getId();
        if (id==-1) {
            UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
            userInfos = forcusService.getFans(userInfo);
        }
        else {
            userInfos = forcusService.getFans(otherUser);
        }
        return new ModelAndView("personal","userInfos",userInfos);
    }

    /**
     * 是否已关注
     */
    @RequestMapping("user/ifForcus")
    public ModelAndView ifForcus(@RequestBody String user,HttpServletRequest request){
        UserInfo otherUser = JSON.parseObject(user, UserInfo.class);
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        Focus focus = new Focus();
        focus.setThisId(userInfo.getId());
        focus.setFriendId(otherUser.getId());
        boolean result = forcusService.ifForcus(focus);
        return  new ModelAndView("personal","result",result);
    }
}
