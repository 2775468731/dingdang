package com.dingdang.user.user.controller;

import com.dingdang.user.user.exception.ExceptionEnum;
import com.dingdang.user.user.entity.User;
import com.dingdang.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:21:32
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 引入日志，注意都是"org.slf4j"包下
     */
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "selectUser/{userId}", method = RequestMethod.GET)
    public User selectUser(@PathVariable String userId, HttpServletResponse httpServletResponse) throws Exception {
        User user = userService.selectUser(userId);
        if (user == null) {
            logger.error("根据员工工号，查询员工异常：e=" + ExceptionEnum.ERROR_NOFOUND.getValue());
//            throw new NoFoundExcepiton(ExceptionEnum.ERROR_NOFOUND.getValue());
        }
        return user;
    }


}
