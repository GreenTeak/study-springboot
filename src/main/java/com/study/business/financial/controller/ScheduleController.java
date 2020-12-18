package com.study.business.financial.controller;

import com.study.business.financial.ScheduleFactory;
import com.study.business.financial.model.ScheduleDetail;
import com.study.business.financial.model.ScheduleInfo;
import com.study.business.financial.service.IScheduleService;
import com.study.common.web.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 还款计划生成
 */
@RestController
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @PostMapping("scheduleDetails")
    public ResponseMessage execute(@RequestBody ScheduleInfo scheduleInfo) {

        logger.info("还款计划明细参数：{}" + scheduleInfo.toString());
        String type = scheduleInfo.getType() == null ? "INTEREST" : scheduleInfo.getType();
        IScheduleService scheduleService = ScheduleFactory.getScheduleService(type);

        // 生成还款计划明细
        List<ScheduleDetail> scheduleDetails = scheduleService.createScheduleDetails(scheduleInfo);

        return ResponseMessage.success(scheduleDetails);
    }
}
