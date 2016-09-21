package com.bs.trade.service.impl;

import com.bs.trade.mapper.ForcusMapper;
import com.bs.trade.model.Focus;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.ForcusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wyn on 2016/5/16.
 */
@Service("forcusService")
public class ForcusServiceImpl extends BaseService<Focus>  implements ForcusService{
    @Autowired
    private ForcusMapper forcusMapper;

    @Override
    public List<UserInfo> getFriend(UserInfo userInfo) {
         Integer id = userInfo.getId();
         return forcusMapper.firendList(id);
    }

    @Override
    public List<UserInfo> getFans(UserInfo userInfo) {
        Integer id = userInfo.getId();
        return forcusMapper.fansList(id);
    }

    @Override
    public boolean ifForcus(Focus focus) {
        Integer id1 = focus.getThisId();
        Integer id2 = focus.getFriendId();
        Focus focus1 = forcusMapper.ifForcus(id1,id2);
        if (focus1 != null){
            return true;
        }
        else{
            return false;
        }
    }
}
