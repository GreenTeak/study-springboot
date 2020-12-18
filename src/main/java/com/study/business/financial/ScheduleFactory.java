package com.study.business.financial;

import com.study.business.financial.service.IScheduleService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.通过spring机制将IScheduleService实现加载到map对象中
 * 2.通过ScheduleEnum枚举类型获取流程实现类
 * 3.通过类别获取对应流程实现实例
 *
 * @date 2020-12-18
 */
@Component
public class ScheduleFactory implements ApplicationContextAware {

    /**
     * 将接口实现类实例保存到Map中
     */
    private static Map<ScheduleEnum, IScheduleService> scheduleServiceMap;

    /**
     * 根据类型获取对应流程实现实例
     *
     * @param type
     * @return
     */
    public static IScheduleService getScheduleService(String type) {
        ScheduleEnum transferFlowEnum = getScheduleMode(type);
        return scheduleServiceMap.get(transferFlowEnum);
    }

    public static IScheduleService getScheduleService(TransferFlowEnum transferFlowEnum) {
        return scheduleServiceMap.get(transferFlowEnum);
    }

    public static ScheduleEnum getScheduleMode(String eventType) {
        switch (eventType) {
            // 资产证券化封包
            case "CAITAL":
                return ScheduleEnum.CAITAL;
            //资产证券化发行
            case "INTEREST":
                return ScheduleEnum.INTEREST;
            default:
                //默认实现为空，应该抛出系统异常
                return null;
        }
    }


    /**
     * 通过spring机制将IScheduleService实例加载到Map对象中
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IScheduleService> map = applicationContext.getBeansOfType(IScheduleService.class);
        scheduleServiceMap = new HashMap<>(map.size());

        for (Map.Entry<String, IScheduleService> entry : map.entrySet()) {
            // 枚举-实例对象
            scheduleServiceMap.put(entry.getValue().getScheduleModel(), entry.getValue());
        }
    }
}
