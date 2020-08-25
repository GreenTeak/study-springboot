package com.study.business.financial.controller;

import com.study.business.financial.ITransferFlow;
import com.study.business.financial.TransferFlowFactory;
import com.study.business.financial.model.TransferInModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 相当于核心中的 TransferMicro
 */
@RestController
public class TransferController {

    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

//    @Autowired
//    ITransferFlow transferFlow;// 不能直接注入。应该利用工厂类去拿实例对象

    @GetMapping("execute")
    public void execute(@RequestParam(value = "eventType", defaultValue = "PACK") String eventType) {

        ITransferFlow transferFlow = TransferFlowFactory.getTransferFlow(eventType);

        logger.info("参数 eventType:{}", eventType);
        TransferInModel transfer = new TransferInModel();
        // 校验
        transferFlow.check(transfer);
        // 执行
        transferFlow.processs(transfer);

    }
}
