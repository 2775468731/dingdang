package com.dingdang.user.service;

import com.dingdang.user.entity.User;
import com.dingdang.user.entity.UserTab;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:21:29
 */
public interface UserService {

    User selectUser(String userId);

    UserTab selectTabUser(String userId);


}
