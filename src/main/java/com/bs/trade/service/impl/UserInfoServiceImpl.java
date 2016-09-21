package com.bs.trade.service.impl;

import com.bs.trade.mapper.UserInfoMapper;
import com.bs.trade.model.UserInfo;
import com.bs.trade.model.UserVo;
import com.bs.trade.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        String email = user.getEmail();
        if (userInfoMapper.selectByEmail(email) != null) {
            return false;
        }
        else{
            userInfoMapper.insert(user);
            return true;
        }
    }

    @Override
    public boolean login(UserInfo user) {
        String email = user.getEmail();
        if (userInfoMapper.selectByEmail(email) != null
                && userInfoMapper.selectByEmail(email).getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<UserInfo> getAll(int page, int rows) {
        PageHelper.orderBy("id desc");
        PageHelper.startPage(page, rows);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        return new PageInfo<UserInfo>(userInfos);
    }

    @Override
    public UserInfo getByEmail(UserInfo userInfo) {
        String email  = userInfo.getEmail();
        return userInfoMapper.selectByEmail(email);
    }


}
