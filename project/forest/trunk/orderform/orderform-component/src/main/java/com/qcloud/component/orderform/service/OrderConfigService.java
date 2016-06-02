package com.qcloud.component.orderform.service;

import java.util.List;

public interface OrderConfigService {

    List<int[]> normalChain();

    int getNormalInitOrderState();

    int getNormalMerchantOrderState(int state);

    int getNormalPersonalOrderState(int state);

    String getNormalPersonalOrderStateDesc(int state);

    List<int[]> refundChain();

    int getRefundInitOrderState();

    int getRefundMerchantOrderState(int state);

    int getRefundPersonalOrderState(int state);

    String getRefundPersonalOrderStateDesc(int state);

    List<int[]> returnChain();

    int getReturnInitOrderState();

    int getReturnMerchantOrderState(int state);

    int getReturnPersonalOrderState(int state);

    String getReturnPersonalOrderStateDesc(int state);

    List<int[]> exchangeChain();

    int getExchangeMerchantOrderState(int state);

    int getExchangePersonalOrderState(int state);

    String getExchangePersonalOrderStateDesc(int state);

    int getExchangeInitOrderState();

    boolean isNormalFinish(int state);

    boolean isRefundFinish(int state);

    boolean isReturnFinish(int state);

    boolean isExchangeFinish(int state);

    boolean canExchangeInNormal(int state);

    boolean canRefundInNormal(int state);

    boolean canReturnInNormal(int state);
}
