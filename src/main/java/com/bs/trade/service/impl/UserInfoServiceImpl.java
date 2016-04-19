package com.bs.trade.service.impl;

import com.bs.trade.mapper.UserInfoMapper;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * Created by wangyanan on 2016/4/6.
 */
@Service("userInfoService")

public class UserInfoServiceImpl extends BaseService<UserInfo> implements UserInfoService {

    @Autowired
    protected UserInfoMapper userInfoMapper;

    @Override
    public boolean register(UserInfo user) {
        userInfoMapper.insert(user);
        if(userInfoMapper.selectByPrimaryKey(user.getName()) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean login(UserInfo user) {
        if(userInfoMapper.selectByPrimaryKey(user.getEmail()) != null
                && userInfoMapper.selectByPrimaryKey(user.getEmail()).getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }
}
