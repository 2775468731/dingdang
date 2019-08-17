package com.dingdang.user.user.exception;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:21:31
 */
public class NoFoundExcepiton extends Exception {

    private static final long serialVersionUID = -5955607821816077172L;

    public NoFoundExcepiton(String errorInfo) {
        super(errorInfo);
    }

}
