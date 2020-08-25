package com.study.common.enumer;

/**
 * 线程池类型枚举类
 *
 * @author zhangpba
 * @date 2019-11-27
 */
public enum ThreadPoolEnum {
    self,       // 自定义线程池
    sched1,     // 这里设置延迟4秒执行
    sched2,     // 设置延迟2秒后每3秒执行一次
    fixed,      // 定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    single,     // 单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
    cached;     // 可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程

}
