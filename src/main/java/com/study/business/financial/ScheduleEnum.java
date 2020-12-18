package com.study.business.financial;


/**
 * 还款计划实现方式枚举类
 *
 * @date 2020-12-18
 */
public enum ScheduleEnum {

    CAITAL("CAITAL"),   // 等额本金
    INTEREST("INTEREST");   // 等额本息

    private String model;

    ScheduleEnum(String model) {
        this.model = model;
    }

    public static ScheduleEnum getScheduleByMode(String model) {

        ScheduleEnum scheduleEnumResult = null;
        try {
            for (ScheduleEnum scheduleEnum : ScheduleEnum.values()) {
                if (scheduleEnum.name().equals(model)) {
                    scheduleEnumResult = scheduleEnum;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scheduleEnumResult;
    }

    public String getModel() {
        return model;
    }
}

