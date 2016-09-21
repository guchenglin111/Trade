package com.bs.trade.mapper;

import com.bs.trade.model.Collect;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.util.AllMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 收藏mapper
 * Created by wangyanan on 2016/5/4.
 */
public interface CollectMapper extends AllMapper<Collect> {

    /**
     * 显示商品信息（按指定属性排序）
     *
     * @param id    商品检索字段ID
     * @return 商品列表
     */
    List<GoodsVo> showCollect(@Param("id") Integer id);

    @Select("select * from collect where goods = #{goods} and user = #{owner}")
    Collect ifCollect(@Param("goods") Integer goods,@Param("owner") Integer owner);
}
