package com.study.business.financial.flow;

import com.study.business.financial.ITransferFlow;
import com.study.business.financial.TransferFlowEnum;
import com.study.business.financial.model.TransferInModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PackFlowImpl implements ITransferFlow {

    private Logger logger = LoggerFactory.getLogger(PackFlowImpl.class);

    @Override
    public TransferFlowEnum getTransferFlowMode() {
        return TransferFlowEnum.PACK;
    }

    @Override
    public void processs(TransferInModel transferInModel) {
        logger.info("PackFlowImpl processs方法");
    }

    @Override
    public void check(TransferInModel transferInModel) {
        logger.info("PackFlowImpl check方法");
    }
}
