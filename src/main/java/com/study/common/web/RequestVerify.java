package com.study.common.web;

import com.study.common.constant.FilterConstant;
import com.study.common.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * request验证
 *
 * @author zhangpba
 * @data 2019-12-27
 */
public class RequestVerify {

    private static final Logger logger = LoggerFactory.getLogger(RequestVerify.class);

    private static final String SPLIT = ",";

    /**
     * 验证ip是否有访问权限
     *
     * @param request
     * @return
     */
    public static boolean checkIp(HttpServletRequest request) {
        AtomicBoolean isIp = new AtomicBoolean(false);
        String requestIp = IpUtil.getIpAddr(request);
        logger.info("访问 ip:{}", requestIp);
        String[] ips = FilterConstant.filter_ips.split(SPLIT);
        Arrays.asList(ips).forEach(ip -> {
            if (ip.equals(requestIp)) {
                isIp.set(true);
            }
        });
        if (!isIp.get()) {
            logger.info("{} 没有访问权限", requestIp);
        }
        return isIp.get();
    }

}
