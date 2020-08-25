package com.study.interceptor;

import com.study.common.web.RequestVerify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @descript 定义拦截器
 * @date 2019-12-05
 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    // preHandle方法 返回false拦截不往下继续进行，返回true继续往下进行，进入controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截器 preHandle()");
        if (handler instanceof HandlerMethod) {
            logger.info("拦截器 获取控制器的名称：{} 获取的方法名：{}", ((HandlerMethod) handler).getBean().getClass().getName(), ((HandlerMethod) handler).getMethod().getName());
        } else {
            logger.info("拦截器 获取控制器的名称：{}",((ResourceHttpRequestHandler)handler).getVaryByRequestHeaders());
        }

        boolean flag = RequestVerify.checkIp(request);
        if (!flag) {
            logger.info("拦截器-拦截没有访问权限");
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("拦截器 postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("拦截器 afterCompletion()");
    }
}
