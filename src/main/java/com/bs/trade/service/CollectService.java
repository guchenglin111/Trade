package com.bs.trade.service;

import com.bs.trade.model.Collect;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.UserInfo;
import com.github.pagehelper.PageInfo;

/**
 * 收藏接口
 * Created by wangyanan on 2016/5/4.
 */

public interface CollectService extends IService<Collect>{
    PageInfo<GoodsVo> showCollect(UserInfo userInfo,int page, int rows);
    boolean ifCollect(Collect collect);
}
