package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.UserInfoService;
import com.bs.trade.session.SessionService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关
 * Created by wangyanan on 2016/4/7.
 */
@Controller
public class UserInfoController {
    private static final Logger LOGGER = Logger.getLogger(UserInfoController.class);


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SessionService sessionService;


    /**
     * 用户注册
     */
    @RequestMapping("user/register")
    public ModelAndView Register(@RequestBody String user) {
        UserInfo curuser = JSON.parseObject(user, UserInfo.class);
        curuser.setGoodsCount(0);
        curuser.setFansCount(0);
        curuser.setFriendCount(0);
        curuser.setAsset(0);
        if (userInfoService.register(curuser)) {
            return new ModelAndView("login", "i", 1);
        }
        return new ModelAndView("register", "i", 0);
    }


    /**
     * 用户登录
     */
    @RequestMapping("user/login")
    public ModelAndView Login(@RequestBody String user, HttpServletRequest request) throws IOException {
        UserInfo curuser = JSON.parseObject(user, UserInfo.class);
        LOGGER.info(curuser.getEmail());
        if (curuser.getEmail().equals("admin@trade.com") && curuser.getPassword().equals("admin")) {
            sessionService.setAttribute(request, Constants.User_SESSION, curuser);
            return new ModelAndView("adminItem","user", "admin");
        }
        if (userInfoService.login(curuser)) {
            sessionService.setAttribute(request, Constants.User_SESSION, userInfoService.getByEmail(curuser));
            String name = userInfoService.getByEmail(curuser).getName();
            if (name == null) {
                return new ModelAndView("index", "user", curuser.getEmail());
            }
            return new ModelAndView("index", "user", name);

        }
        return new ModelAndView("login", "user", "false");
    }

    /**
     * 注销
     */
    @RequestMapping("user/quit")
    public ModelAndView backLogin(HttpServletRequest request) {
        sessionService.logout(request);
        return new ModelAndView("index");
    }

    /**
     * 用户中心显示用户信息
     */
    @RequestMapping("user/show")
    public ModelAndView showUser(@RequestBody String user, HttpServletRequest request) {
        UserInfo otherUser = JSON.parseObject(user, UserInfo.class);
        Integer id = otherUser.getId();
        Map<String,Object> result = Maps.newHashMap();
        UserInfo showUser = new UserInfo();
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        Integer sessionId = userInfo.getId();
        if (id == -1) {
            showUser = userInfoService.selectByKey(userInfo);
            result.put("user", showUser);
            result.put("ifSelf", true);
        } else if (id == sessionId) {
            showUser = userInfoService.selectByKey(userInfo);
            result.put("user", showUser);
            result.put("ifSelf", true);
        } else {
            showUser = userInfoService.selectByKey(otherUser);
            result.put("user", showUser);
            result.put("ifSelf", false);
        }


        return new ModelAndView("personal", result);
    }

    /**
     * 修改个人消息时判断旧密码输入是否正确
     */
    @RequestMapping("user/judgePass")
    public ModelAndView judge(@RequestBody String user, HttpServletRequest request) {
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        UserInfo sessionUser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        String oldPass = sessionUser.getPassword();
        String newPass = userInfo.getPassword();
        if (oldPass.equals(newPass)) {
            return new ModelAndView("personal", "result", true);

        } else {
            return new ModelAndView("personal", "result", false);
        }
    }


    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    @RequestMapping("user/update")
    public ModelAndView update(@RequestBody String user, HttpServletRequest request) {
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        UserInfo sessionUser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        userInfo.setId(sessionUser.getId());
        userInfoService.updateNotNull(userInfo);
        return new ModelAndView("personal");
    }

    /**
     * 管理员用户列表
     */
    @RequestMapping("admin/user")
    public ModelAndView getAllUser(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "5") int rows) {
        ModelAndView result = new ModelAndView("adminUser");
        PageInfo<UserInfo> userList = userInfoService.getAll(page, rows);
        result.addObject("pageInfo", userList);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }

    /**
     * 管理员管理用户
     */
    @RequestMapping("admin/user/delete")
    public ModelAndView delete(@RequestBody String user) {
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        userInfoService.delete(userInfo);
        return new ModelAndView("adminUser");
    }


    /**
     * 充值
     *
     * @param user 用户信息
     */
    @RequestMapping("user/addMoney")
    public ModelAndView addMoney(@RequestBody String user, HttpServletRequest request) {
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        UserInfo sessionUser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        userInfo.setId(sessionUser.getId());
        UserInfo dbUser = userInfoService.getByEmail(sessionUser);
        userInfo.setAsset(userInfo.getAsset() + dbUser.getAsset());
        userInfoService.updateNotNull(userInfo);
        return new ModelAndView("personal");
    }
}
