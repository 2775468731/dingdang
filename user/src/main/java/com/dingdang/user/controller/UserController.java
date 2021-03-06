package com.dingdang.user.controller;

import com.dingdang.user.entity.UserTab;
import com.dingdang.user.service.UserService;
import common.BaseReturn;
import exception.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${mybatis.mapper-locations}")
    public String mapperLocations;

    @RequestMapping(value = "selectUser/{userId}", method = RequestMethod.GET)
    public BaseReturn selectUser(@PathVariable String userId, HttpServletResponse httpServletResponse) throws Exception {
        UserTab user = userService.selectTabUser(userId);
        if (user == null) {
            logger.error(" 根据员工工号，查询员工异常：e=" + ExceptionEnum.ERROR_NOFOUND.getValue());
//            throw new NoFoundExcepiton(ExceptionEnum.ERROR_NOFOUND.getValue());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        return new BaseReturn("操作成功",map);
    }


}
