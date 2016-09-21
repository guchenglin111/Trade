package com.bs.trade.mapper;

import com.bs.trade.model.UserInfo;
import com.bs.trade.model.UserVo;
import com.bs.trade.util.AllMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by RI01796 on 2016/4/6.
 */
public interface UserInfoMapper extends AllMapper<UserInfo> {
    List<UserVo> adminUser(@Param("id") Integer id);

    @Select("select id,name,password,portrait,phone,email,asset,goods_count as goodsCount,friend_count as friendCount,fans_count as fansCount" +
            " from user_info where email = #{email}")
    UserInfo selectByEmail(@Param("email") String email);
}
