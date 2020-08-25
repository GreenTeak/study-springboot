package com.study.business.financial;


/**
 * 资产证券化实现方式枚举类
 */
public enum TransferFlowEnum {

    PACK("PACK"),   // 封包
    SALE("SALE"),   // 发行
    NEWCT("NEWCT"), // 删选、维护
    TRFA("TRFA"),   // 资产转让
    VTRFA("VTRFA"), // 资产转让复核
    UNPACK("UNPACK"),// 发行撤销-拆包
    REVOKE("REVOKE"),// 发行撤销
    REDEEM("REDEEM");// 赎回

    private String model;

    TransferFlowEnum(String model) {
        this.model = model;
    }

    public static TransferFlowEnum getTransferFlowByMode(String model) {

        TransferFlowEnum transferFlowEnumResult = null;
        try {
            for (TransferFlowEnum transferFlowEnum : TransferFlowEnum.values()) {
                if (transferFlowEnum.name().equals(model)) {
                    transferFlowEnumResult = transferFlowEnum;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transferFlowEnumResult;
    }

    public String getModel() {
        return model;
    }
}

