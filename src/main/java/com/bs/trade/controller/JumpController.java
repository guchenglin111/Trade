package com.bs.trade.controller;

import com.bs.trade.model.UserInfo;
import com.bs.trade.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 跳转类
 * Created by wyn on 2016/5/14.
 */
@Controller
public class JumpController {

    @Autowired
    private SessionService sessionService;

    /**
     * 个人中心
     */
    @RequestMapping(value = {"personal"})
    public ModelAndView personal(HttpServletRequest request) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("personal");
        }
    }

    /**
     * 商品发布页
     */
    @RequestMapping(value = {"publish"})
    public ModelAndView publish(HttpServletRequest request) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("publish");
        }
    }

    /**
     * 登录页
     */
    @RequestMapping(value = {"login"})
    public ModelAndView Login() {
        return new ModelAndView("login");
    }


    /**
     * 注册页
     */
    @RequestMapping(value = {"register"})
    public ModelAndView admin() {
        return new ModelAndView("register");
    }

    /**
     * 商品详情页
     */
    @RequestMapping(value = {"shop"})
    public ModelAndView shop() {
        return new ModelAndView("shop");
    }

    /**
     * 管理员管理商品
     */
    @RequestMapping(value = {"adminItem"})
    public ModelAndView adminItem(HttpServletRequest request) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("adminItem");
        }
    }

    /**
     * 管理员管理用户
     */
    @RequestMapping(value = {"adminUser"})
    public ModelAndView adminUser(HttpServletRequest request) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        if (curuser == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("adminUser");
        }
    }

    /**
     * 商品列表页
     */
    @RequestMapping(value = {"goodsList"})
    public ModelAndView show() {
        return new ModelAndView("goodsList");
    }

}
