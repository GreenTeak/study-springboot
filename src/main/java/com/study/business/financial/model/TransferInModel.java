package com.study.business.financial.model;

import java.math.BigDecimal;
import java.util.List;

public class TransferInModel {

    private String trfFlag;             //转让类型 01-转让 02-回购
    private String newProdType;         //转让后产品类型
    private String contractNo;          //转让合同号
    private String reason;              //资产转让原因
    private String reaPackCancle;       //资产证券化撤包原因
    private BigDecimal balance;         //贷款余额
    private String intFlag;             //利息转出标志 T-按拆分日 A-全部转出 N-不转出
    private String odpFlag;             //罚息转出标志
    private String odiFlag;             //复利转出标志
    private String packDate;            //封包日期、利息起算日期
    private String transferType;        //转让类型：1：转让  2：回购
    private String saleDate;            //发行日期、转让日期
    private String floatCalcFlag;       //折溢价计算方式  A:整体结算 D:跟贷款明细结算
    private BigDecimal contractAmt;     //合同金额
    private BigDecimal floatAmt;        //折溢价
    private BigDecimal settlePriAmt;    //转让受让方本金
    private BigDecimal settleIntAccrAmt;//转让前应计利息
    private BigDecimal settleIntOut;    //转让前应收利息
    private BigDecimal settleOdpAccrAmt;//转让前应计罚息
    private BigDecimal settleOdpOut;    //转让前应收罚息
    private BigDecimal settleOdiAccrAmt;//转让前应计复利
    private BigDecimal settleOdiOut;    //转让前应收复利
    private BigDecimal settleRecInt;    //全部转让时受让方应收利息
    private BigDecimal settleRecOdp;    //全部转让时受让方应收罚息
    private BigDecimal settleRecOdi;    //全部转让时受让方应收复利
    private String approvalStatus;      //复核标志
    private String agreementStatus;     //合同状态
    private String contractId;          //合同主表ID
    private String pactType;            //合同类型
    private String resSeqNo;
    private BigDecimal settleAmt;
    private String transferSeqNo;

    private List<TransferInModel> transferDetailModels;
    private List<TransferSettleModel> transferSettleModels;
}
