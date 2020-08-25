package com.study.common.service.impl;

import com.study.common.enumer.ThreadPoolEnum;
import com.study.common.service.ThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 线程池参数解释：
 * corepoolsize:    核心池大小，创建线程之后，线程池数量为0，有任务+1，直到数量达到corepoolsize后，把任务放在缓存队列
 * maximumpoolsize: 线程池最多创建多少个线程
 * keepalivetime:   线程中没有任务执行，最多保持多久时间会终止，只有线程数量大于corepoolsize这个机制才会生效
 * WorkQueue:       阻塞队列，要用来存放等待被执行的任务
 * ThreadFactory:   线程工厂，用来创建线程
 */
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService, InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolServiceImpl.class);

    // 线程池
    private ThreadPoolExecutor selfPool = null;
    private ScheduledExecutorService scheduledPool = null;
    private ExecutorService fixedThreadPool = null;
    private ExecutorService singleThreadPool = null;
    private ExecutorService cachelThreadPool = null;

    /**
     * 初始化线程池
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("InitializingBean.afterPropertiesSet() 允许一个bean在它的所有必须属性被BeanFactory设置后，来执行初始化的工作");

        // 自定义线程池-常用
        selfPool = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1000));

        // 定长线程池，支持定时及周期性任务执行
        scheduledPool = Executors.newScheduledThreadPool(2);

        // 设置最大线程数5个
        fixedThreadPool = Executors.newFixedThreadPool(5);

        // 单线程化的线程池
        singleThreadPool = Executors.newSingleThreadExecutor();

        // 可缓存线程池
        cachelThreadPool = Executors.newCachedThreadPool();
    }

    /**
     * 关闭线程池
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        logger.info("DisposableBean.destroy() 允许在容器销毁该bean的时候获得一次回调");

        selfPool.shutdown();
        scheduledPool.shutdown();
        fixedThreadPool.shutdown();
        singleThreadPool.shutdown();
        cachelThreadPool.shutdown();
    }


    /**
     * 测试方法
     *
     * @param poolType 线程池类型
     */
    @Override
    public void test(String poolType) {
        // 将字符串转化为枚举实例
        ThreadPoolEnum poolEnum = ThreadPoolEnum.valueOf(poolType);
        for (int i = 0; i < 10; i++) {
            switch (poolEnum) {
                case sched1:
                    // 这里设置延迟4秒执行
                    scheduledPool.schedule(new Work(poolType), 4, TimeUnit.SECONDS);
                    break;
                case sched2:
                    // 设置延迟2秒后每3秒执行一次
                    scheduledPool.scheduleAtFixedRate(new Work(poolType), 2, 3, TimeUnit.SECONDS);
                    break;
                case fixed:
                    fixedThreadPool.execute(new Work(poolType));
                    break;
                case cached:
                    cachelThreadPool.execute(new Work(poolType));
                    break;
                case single:
                    singleThreadPool.execute(new Work(poolType));
                    break;
                default:
                    selfPool.execute(new Work(poolType));
                    break;
            }

        }
    }


    // 任务
    class Work implements Runnable {

        private String poolType;

        public void setPoolType(String poolType) {
            this.poolType = poolType;
        }

        public Work(String poolType) {
            this.poolType = poolType;
        }

        @Override
        public void run() {
            logger.info("{}线程池，任务线程:{} 要执行的任务代码.....", poolType, Thread.currentThread().getName());
        }
    }


    public void testException() {
        int s = 1 / 0;
    }
}
