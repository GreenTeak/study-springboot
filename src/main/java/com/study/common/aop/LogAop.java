package com.study.common.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    /**
     * 声明一个切点
     * 声明service包下的所有方法都会进行切面。
     */
    @Pointcut("execution(* com.study.common.service.*.*(..))")
    public void outlog() {
        logger.info("切点");
    }

    @Before("outlog()")//在方法执行前切入
    public void beforeCheck(JoinPoint joinPoint) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();

        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);

        logger.info("前置方法:我在方法{}前面，参数为：{}", method, params);
        //打印请求参数
        logger.info(method + ":" + params);
    }

    @After("outlog()")//在方法执行后切入
    public void after() {
        logger.info("后置方法:我在方法后面");
    }

//    @Around("outlog()")//环绕整个方法的前后
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info("进入方法前---环绕通知");
//        // 记录起始时间
//        long begin = System.currentTimeMillis();
//        Object result = "";
//        // 执行目标方法
//        try {
//            result = joinPoint.proceed();
//        } catch (Exception e) {
//            logger.error("日志记录发生错误, errorMessage: {}", e.getMessage());
//        } finally {
//            // 记录操作时间
//            long took = (System.currentTimeMillis() - begin) / 1000;
//            logger.info("Service执行时间为: {}秒", took);
//        }
//        logger.info("退出方法后---环绕通知");
//        return result;
//    }


}
