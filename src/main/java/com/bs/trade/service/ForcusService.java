package com.bs.trade.service;

import com.bs.trade.model.Focus;
import com.bs.trade.model.UserInfo;

import java.util.List;

/**
 * Created by wyn on 2016/5/16.
 */
public interface ForcusService extends IService<Focus>{

    List<UserInfo> getFriend(UserInfo userInfo);
    List<UserInfo> getFans(UserInfo userInfo);
    boolean ifForcus(Focus focus);

}
