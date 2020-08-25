package com.study.business.financial;

import com.study.business.financial.model.TransferInModel;

public interface ITransferFlow {

    TransferFlowEnum getTransferFlowMode();

    void processs(TransferInModel transferInModel);

    void check(TransferInModel transferInModel);
}
