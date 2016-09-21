package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.Message;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.MessageService;
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
 * 消息控制类
 * Created by wyn on 2016/5/4.
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SessionService sessionService;

    /**
     * 读消息
     */

    @RequestMapping("user/read")
    public void readMessage(@RequestBody String message) {
        Message message1 = JSON.parseObject(message, Message.class);
        message1.setIfRead(1);
        messageService.updateNotNull(message1);
    }

    /**
     * 获取消息列表
     */
    @RequestMapping("user/message")
    public List<Message> getMessage(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        Message message = new Message();
        List<Message> messages = Lists.newArrayList();
        if (userInfo == null) {
            return messages;
        } else {
            message.setReceiver(userInfo.getId());
            messages = messageService.query(message);
            return messages;
        }

    }
}
