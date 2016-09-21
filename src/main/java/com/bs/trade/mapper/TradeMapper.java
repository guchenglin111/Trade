package com.bs.trade.mapper;

import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.Trade;
import com.bs.trade.util.AllMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 交易mapper
 * Created by wangyanan on 2016/5/13.
 */
public interface TradeMapper extends AllMapper<Trade> {

    /**
     * 显示商品信息（按指定属性排序）
     *
     * @param id    商品检索字段ID
     * @return 商品列表
     */
    List<GoodsVo> showTrade(@Param("id") Integer id);
    List<GoodsVo> showBorrow(@Param("id") Integer id);

    @Select("select * from circulate_info where lender = #{lender} and goods = #{goods} and circulate_status=1")
    Trade ifTrade(@Param("lender") Integer lender,@Param("goods") Integer goods);

}
