package com.bs.trade.service.impl;

import com.bs.trade.mapper.CollectMapper;
import com.bs.trade.model.Collect;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.CollectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏实现类
 * Created by wangyanan on 2016/5/4.
 */
@Service("collectService")
public class CollectServiceImpl extends BaseService<Collect>  implements CollectService{

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public PageInfo<GoodsVo> showCollect(UserInfo userInfo, int page, int rows) {
        Integer id = userInfo.getId();
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = collectMapper.showCollect(id);
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public boolean ifCollect(Collect collect) {
        Integer goods = collect.getGoods();
        Integer owner = collect.getUser();
        Collect c = collectMapper.ifCollect(goods,owner);
        if (c==null){
            return false;//未收藏
        }
        else{
            return true;//已收藏
        }
    }
}
