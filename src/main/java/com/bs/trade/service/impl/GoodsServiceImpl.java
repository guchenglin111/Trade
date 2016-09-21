package com.bs.trade.service.impl;

import com.bs.trade.mapper.GoodsMapper;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 物品相关操作实现
 * Created by wangyanan on 2016/4/7.
 */
@Service("goodsService")
public class GoodsServiceImpl extends BaseService<GoodsInfo> implements GoodsService {

    @Autowired
    protected GoodsMapper goodsMapper;


    @Override
    public PageInfo<GoodsVo> selectGoodsInfo(GoodsInfo goodsInfo, int page, int rows, String sort) {
        Integer id = goodsInfo.getId();
        Integer goodType = goodsInfo.getType();
        String goodName = goodsInfo.getName();
        String goodRegion = goodsInfo.getRegion();
        PageHelper.orderBy(sort + " desc");
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = goodsMapper.showGoodsInfo(id, goodName, goodRegion, goodType);
      /*  for (GoodsVo goodsVo : goodsVos) {
            if (goodsVo.getTypeId().equals(6)) {
                goodsVos.remove(goodsVo);
            }
        }*/
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public PageInfo<GoodsVo> selectGoodsInfo(GoodsInfo goodsInfo, int page, int rows) {
        Integer id = goodsInfo.getId();
        Integer goodType = goodsInfo.getType();
        String goodName = goodsInfo.getName();
        String goodRegion = goodsInfo.getRegion();
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = goodsMapper.showGoodsInfo(id, goodName, goodRegion, goodType);
      /*  for (GoodsVo goodsVo : goodsVos) {
            if (goodsVo.getTypeId().equals(6)) {
                goodsVos.remove(goodsVo);
            }
        }*/
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public PageInfo<GoodsVo> selectAny(String search, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = goodsMapper.selectAlls(search);
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public PageInfo<GoodsVo> adminGoodsInfo(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = goodsMapper.adminGoodsInfo();
        return new PageInfo<GoodsVo>(goodsVos);
    }


    @Override
    public PageInfo<GoodsVo> selectMoney(GoodsInfo goodsInfo, int page, int rows, String sort) {
        Integer id = goodsInfo.getId();
        Integer goodType = goodsInfo.getType();
        String goodName = goodsInfo.getName();
        String goodRegion = goodsInfo.getRegion();
        PageHelper.orderBy(sort + " desc");
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = goodsMapper.showGoodsInfo(id, goodName, goodRegion, goodType);
        for (GoodsVo goodsVo : goodsVos) {
            if (!goodsVo.getTypeId().equals(6)) {
                goodsVos.remove(goodsVo);
            }
        }
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public GoodsVo getGoodsInfo(GoodsVo goodsVo) {
        Integer id = goodsVo.getId();
        return goodsMapper.getGoodsInfo(id);
    }

    @Override
    public GoodsVo adminGetGoodsInfo(GoodsVo goodsVo) {
        Integer id = goodsVo.getId();
        return goodsMapper.adminGetGoodsInfo(id); }

    @Override
    public int getListOne() {
        return goodsMapper.getListOne();
    }

    @Override
    public PageInfo<GoodsVo> showAdd(UserInfo userInfo, int page, int rows) {
        Integer owner = userInfo.getId();
        PageHelper.startPage(page, rows);
        List<GoodsVo> goodsVos = goodsMapper.showAdd(owner);
        return new PageInfo<GoodsVo>(goodsVos);
    }

    @Override
    public List<GoodsInfo> selectByGoods(GoodsInfo goodsInfo, int page, int rows) {
        Example example = new Example(GoodsInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(goodsInfo.getName())) {
            criteria.andLike("name", "%" + goodsInfo.getName() + "%");
        }
        if (StringUtil.isNotEmpty(goodsInfo.getRegion())) {
            criteria.andLike("region", "%" + goodsInfo.getRegion() + "%");
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }


}
