package com.study.filter;

import com.study.common.web.RequestVerify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * https://blog.csdn.net/heweimingming/article/details/79993591
 *
 * @descript 过滤器-方式一（Config注册）
 * @date 2019-12-25
 */
public class MyfilterOne implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MyfilterOne.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器一 init()");
    }

    @Override
    public void destroy() {
        logger.info("过滤器一 destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // do something 处理request或response
        logger.info("过滤器一 doFilter()");
        boolean flag = RequestVerify.checkIp((HttpServletRequest) servletRequest);
        if (flag) {
            // 调用filter链接的下一个filter
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.info("被过滤器一过滤掉 没有访问权限");
            return;
        }
    }


}
