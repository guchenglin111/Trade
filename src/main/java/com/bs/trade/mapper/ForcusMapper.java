package com.bs.trade.mapper;

import com.bs.trade.model.Focus;
import com.bs.trade.model.UserInfo;
import com.bs.trade.util.AllMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wyn on 2016/5/16.
 */
public interface ForcusMapper extends AllMapper<Focus> {

    @Select("select f.friend_id as id, u.name as name from focus as f,user_info as u where f.this_id = #{id} and f.friend_id = u.id ")
    List<UserInfo> firendList(@Param("id") Integer id);

    @Select("select f.this_id as id, u.name as name from focus as f,user_info as u where f.friend_id = #{id} and f.this_id = u.id ")
    List<UserInfo> fansList(@Param("id") Integer id);

    @Select("select * from focus where this_id = #{id1} and friend_id = #{id2}")
    Focus ifForcus(@Param("id1") Integer id1,@Param("id2") Integer id2);
}
