package com.dingdang.user.service.impl;

import com.dingdang.user.entity.UserTab;
import com.dingdang.user.entity.UserTabExample;
import com.dingdang.user.mapper.UserMapper;
import com.dingdang.user.entity.User;
import com.dingdang.user.mapper.UserTabMapper;
import com.dingdang.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:21:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTabMapper userTabMapper;


    @Override
    public User selectUser(String userId) {
        return userMapper.selectUser(userId);
    }

    @Override
    public UserTab selectTabUser(String userId) {
        UserTabExample userTabExample = new UserTabExample();
        userTabExample.createCriteria().andUserIdEqualTo(userId);
        List<UserTab> userTabs = userTabMapper.selectByExample(userTabExample);
        if( userTabs.size()>0 ){
            return userTabs.get(0);
        }
        return null;
    }
}
