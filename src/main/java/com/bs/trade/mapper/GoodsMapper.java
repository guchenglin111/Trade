package com.bs.trade.mapper;

import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.util.AllMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 物品的通用mapper
 * Created by wangyana on 2016/4/7.
 */
public interface GoodsMapper extends AllMapper<GoodsInfo> {

    /**
     * 显示物品信息（按指定属性排序）
     *
     * @param id     物品检索字段ID
     * @param name   物品检索字段名称
     * @param region 物品检索字段 地区
     * @param type   物品检索字段 类型
     * @return 物品列表
     */
    List<GoodsVo> showGoodsInfo(@Param("id") Integer id,
                                @Param("name") String name,
                                @Param("region") String region,
                                @Param("type") Integer type);


    GoodsVo getGoodsInfo(@Param("id") Integer id);

    GoodsVo adminGetGoodsInfo(@Param("id") Integer id);



    List<GoodsVo> adminGoodsInfo();

    @Select("select max(id) from goods")
    int getListOne();

    @Select("select  g.id as id ,g.name as name ,g.type as typeId,t.name as typeName,g.trade_cost as tradeCost,g.image_url as imageUrl,c.time as pubTime" +
            " from goods as g,goods_type as t ,goods_check as c" +
            " where g.owner = #{owner}" +
            " and g.status = 1" +
            " and g.id = c.goods" +
            " and g.type = t.id")
    List<GoodsVo> showAdd(@Param("owner") Integer owner);

    List<GoodsVo> selectAlls(@Param("search") String search);
}
