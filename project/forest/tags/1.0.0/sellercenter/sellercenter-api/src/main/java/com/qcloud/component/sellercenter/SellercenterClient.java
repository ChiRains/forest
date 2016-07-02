package com.qcloud.component.sellercenter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.qcloud.component.publicdata.KeyValueVO;

public interface SellercenterClient {

    String MERCHANT_LOGIN_PARAMETER_KEY     = "sellercenter-login-merchant";

    String APP_MERCHANT_LOGIN_PARAMETER_KEY = "sellercenter-login-merchant-app";

    String STORE_LOGIN_PARAMETER_KEY        = "sellercenter-login-store";

    String APP_STORE_LOGIN_PARAMETER_KEY    = "sellercenter-login-store-app";

    int    SEARCH_TYPE                      = 2;

    //
    QMerchant getMerchant(long merchantId);

    QStore getStore(long storeId);

    List<QStore> listStoreByMerchant(long merchantId);

    boolean addMerchantEvaluation(long evaluationId, long merchantId, long merchandiseId, String content);

    boolean deleteMerchantEvaluation(long evaluationId, long merchantId);

    boolean sendMsgToMerchant(long merchantId, SellerMessageType type, String title, String content);

    boolean sendMsgToStore(long storeId, SellerMessageType type, String title, String content);

    boolean sendSmsToMerchant(long merchantId, String code, Map<String, String> map);

    boolean sendSmsToStore(long storeId, String code, Map<String, String> map);

    boolean addMerchantOrderForm(long merchantId, long orderId, long subOrderId, long storeId, int state, Date time);

    boolean updateOrderFormState(long merchantId, long subOrderId, int state);

    boolean updateOrderFormStore(long merchantId, long subOrderId, long storeId);

    double calculatePostage(String expressCode, long merchantId, double weight, String city);

    List<KeyValueVO> listExpress(QMerchant merchant);

    String getExpressName(String expressCode);

    boolean calculateMyWealth(long userId, WealthType type, double cash, boolean needProportion, String desc);

    QMerchantWealth getMyWealth(long userId);

    QMerchant getMerchant(String code);
}
