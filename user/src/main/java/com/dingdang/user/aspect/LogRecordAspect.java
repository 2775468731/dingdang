package com.dingdang.user.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import util.GenerateJrnUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.LinkedList;

import static com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetSerializer.UTF_8;


/**
 * Title: 日志记录切面
 * Description:
 * author :xbl
 * Date:2019/8/15
 * Time:9:27
 */
@Aspect
@Component
public class LogRecordAspect {


    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    static ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切点Pointcut
     * 第一个*号：表示返回类型， *号表示所有的类型
     * 第二个*号：表示类名，*号表示所有的类
     * 第三个*号：表示方法名，*号表示所有的方法
     * 后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(*  com.dingdang.user.controller.*.*(..))")
    public void executionService() {
    }


    /**
     * @Title: 前置切点
     * @Description:
     * @Author: xbl
     * @CreateDate: 2019/8/15 17:00
     */
    @Before("executionService()")
    public void exBefore(JoinPoint pjp) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        startTime.set(System.currentTimeMillis());
        StringBuffer sb = null;
        String requestId = GenerateJrnUtil.generateJrnByTime();
        MDC.put("requestId",requestId);
        try {
            String uri = request.getRequestURI();
            logger.info("uri:{}", uri);
            sb = getRequestParm(pjp);
            if( sb!=null ){
                logger.info("requestBody:{}", sb);
            }
        } catch (Exception e) {
            logger.error("异常信息：{}", e.getMessage());
        }
    }


    /**
     * @Title: 环绕切点
     * @Description:
     * @Author: xbl
     * @CreateDate: 2019/8/15 16:43
     */
    @Around(value = "executionService()")
    public Object doControlleraround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = getResponseParm(pjp);
        MDC.clear();
        return result;
    }


    /**
     * @Title:获取返回参数
     * @Description:
     * @Author: xbl
     * @CreateDate: 2019/8/15 17:29
     */
    public static Object getResponseParm(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String method = request.getMethod();
        Object[] args = pjp.getArgs();
        String params = "";
        Object startTimeObj = startTime.get();
        if (args.length > 0) {
            if ("POST".equals(method)) {
                Object object = args[0];
                if (object instanceof MultipartFile || object instanceof ServletRequest || object instanceof ServletResponse) {
                    return result;
                }
                params = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
            } else if ("GET".equals(method)) {
                String queryString = request.getQueryString();
                params = queryString;
            }
            if( params!=null ){
                params = URLDecoder.decode(params, UTF_8);
                logger.info("Params:{}", params);
            }
            long endTime = System.currentTimeMillis();
            logger.info("responseBody:{}", JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            if( startTimeObj instanceof Long ){
                long startTime = (long) startTimeObj;
                logger.info("requireTime:{}ms", (endTime - startTime));
            }
        }
        return result;
    }


    /**
     * 获取请求参数
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static StringBuffer getRequestParm(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        StringBuffer sb = null;
        if (args.length>0) {
            Object object = args[0];
            if (object instanceof MultipartFile || object instanceof ServletRequest || object instanceof ServletResponse||object instanceof LinkedList) {
                return null;
            }
            sb = new StringBuffer();
            sb.append(JSONObject.toJSONString(object));
        }
        return sb;
    }
}