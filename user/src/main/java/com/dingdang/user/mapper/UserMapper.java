package com.dingdang.user.mapper;

import com.dingdang.user.entity.User;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:21:28
 */
public interface UserMapper {

    @Select("SELECT id, user_id userId, user_name userName FROM t_user_tab WHERE user_id = #{userId}")
    @ResultType(User.class)
    User selectUser(String userId);

}
