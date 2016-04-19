package com.bs.trade.controller;

import com.bs.trade.model.UserInfo;
import com.bs.trade.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 用户相关
 * Created by wangyanan on 2016/4/7.
 */
@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value="register")
    public String Register(@RequestParam UserInfo user){
        if(userInfoService.register(user)){
            return "0";
        }
        return "1";
    }



    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value="login")
    public String Login(@RequestParam UserInfo user,HttpSession session){
        if(userInfoService.login(user)){
            session.setAttribute("user", user);
            return "success";
        }
        return "fail";
    }


    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping(value="loginOut")
    public String backLogin(HttpSession session){
        session.invalidate();
        return "success";
    }

}
