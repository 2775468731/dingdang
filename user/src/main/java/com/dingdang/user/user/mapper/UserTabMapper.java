package com.dingdang.user.user.mapper;

import com.dingdang.user.user.entity.UserTab;
import com.dingdang.user.user.entity.UserTabExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTabMapper {
    int countByExample(UserTabExample example);

    int deleteByExample(UserTabExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserTab record);

    int insertSelective(UserTab record);

    List<UserTab> selectByExample(UserTabExample example);

    UserTab selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserTab record, @Param("example") UserTabExample example);

    int updateByExample(@Param("record") UserTab record, @Param("example") UserTabExample example);

    int updateByPrimaryKeySelective(UserTab record);

    int updateByPrimaryKey(UserTab record);
}