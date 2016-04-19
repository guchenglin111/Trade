package com.bs.trade.service;

import com.bs.trade.model.UserInfo;

/**
 * 用户操作接口
 * Created by wangyanan on 2016/4/6.
 */
public interface UserInfoService extends IService<UserInfo>{

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(UserInfo user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    boolean login(UserInfo user);
}
