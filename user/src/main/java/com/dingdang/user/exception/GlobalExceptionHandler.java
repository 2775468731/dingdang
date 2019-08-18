package com.dingdang.user.exception;

import common.BaseReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:21:31
 */
@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public BaseReturn processException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        ex.printStackTrace();
        if (ex instanceof MissingServletRequestParameterException) {
            return new BaseReturn(400, ex.getMessage(), null);
        }
        if (ex instanceof NoFoundExcepiton) {
            LOGGER.error("=======" + ex.getMessage() + "=======");
            return new BaseReturn(401, "无法找到相应数据！", null);
        }
        return new BaseReturn(500, ex.getMessage(), null);
    }

}
