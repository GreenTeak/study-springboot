//package com.study.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
///**
// * @descript 过滤器-方式二（注解注册）
// * @date 2019-12-25
// */
//
//@Component                                                  // 注入spring容器
//@WebFilter(filterName = "myfilterTwo", urlPatterns = "/*")  // 定义filterName和过滤的url
//public class MyfilterTwo implements Filter {
//    private static final Logger logger = LoggerFactory.getLogger(MyfilterTwo.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("过滤器二 init()");
//    }
//
//    @Override
//    public void destroy() {
//        logger.info("过滤器二 destroy()");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("过滤器二 doFilter()");
//    }
//}
