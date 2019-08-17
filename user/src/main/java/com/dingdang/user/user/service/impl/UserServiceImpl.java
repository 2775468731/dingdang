package com.dingdang.user.user.service.impl;

import com.dingdang.user.user.mapper.UserMapper;
import com.dingdang.user.user.entity.User;
import com.dingdang.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public User selectUser(String userId) {
        return userMapper.selectUser(userId);
    }
}
