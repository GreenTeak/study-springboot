package com.study.business.financial;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.通过spring机制将IRecFlow实现加载到map对象中
 * 2.通过RecFlowMode枚举类型获取流程实现类
 * 3.通过产品类别获取对应流程实现实例
 */
@Component
public class TransferFlowFactory implements ApplicationContextAware {

    /**
     * 将接口实现类实例保存到Map中
     */
    private static Map<TransferFlowEnum, ITransferFlow> transferFlowMap;

    /**
     * 根据产品类型获取对应流程实现实例
     *
     * @param eventType
     * @return
     */
    public static ITransferFlow getTransferFlow(String eventType) {
        TransferFlowEnum transferFlowEnum = getTransferFlowMode(eventType);
        return transferFlowMap.get(transferFlowEnum);
    }

    public static ITransferFlow getTransferFlow(TransferFlowEnum transferFlowEnum) {
        return transferFlowMap.get(transferFlowEnum);
    }

    public static TransferFlowEnum getTransferFlowMode(String eventType) {
        switch (eventType) {
            // 资产证券化封包
            case "PACK":
                return TransferFlowEnum.PACK;
            //资产证券化发行
            case "SALE":
                return TransferFlowEnum.SALE;
            //资产证券化筛选
            case "NEWCT":
                return TransferFlowEnum.NEWCT;
            //资产转让
            case "TRFA":
                return TransferFlowEnum.TRFA;
            //资产转让
            case "VTRFA":
                return TransferFlowEnum.VTRFA;
            //资产证券化撤包
            case "UNPACK":
                return TransferFlowEnum.UNPACK;
            //资产证券化发行撤销
            case "REVOKE":
                return TransferFlowEnum.REVOKE;
            //资产证券化赎回
            case "REDEEM":
                return TransferFlowEnum.REDEEM;
            default:
                //默认实现为空，应该抛出系统异常
                return null;
        }
    }


    /**
     * 通过spring机制将ITransferFlow实例加载到Map对象中
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ITransferFlow> map = applicationContext.getBeansOfType(ITransferFlow.class);
        transferFlowMap = new HashMap<>(map.size());

        for (Map.Entry<String, ITransferFlow> entry : map.entrySet()) {
            // 枚举-实例对象
            transferFlowMap.put(entry.getValue().getTransferFlowMode(), entry.getValue());
        }
    }
}
