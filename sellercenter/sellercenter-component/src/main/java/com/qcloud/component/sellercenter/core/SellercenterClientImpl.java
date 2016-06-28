package com.qcloud.component.sellercenter.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QMerchantWealth;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellerMessageType;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.WealthType;
import com.qcloud.component.sellercenter.entity.MerchantEntity;
import com.qcloud.component.sellercenter.entity.MerchantWealthEntity;
import com.qcloud.component.sellercenter.entity.StoreEntity;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.key.TypeEnum;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantOrderStateType;
import com.qcloud.component.sellercenter.model.key.TypeEnum.NotifyType;
import com.qcloud.component.sellercenter.model.key.TypeEnum.SexpressType;
import com.qcloud.component.sellercenter.service.MerchantEvaluationService;
import com.qcloud.component.sellercenter.service.MerchantOrderFormService;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.component.sellercenter.service.MerchantWealthService;
import com.qcloud.component.sellercenter.service.SexpressDistrictService;
import com.qcloud.component.sellercenter.service.SexpressService;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class SellercenterClientImpl implements SellercenterClient {

    @Autowired
    protected MerchantService          merchantService;

    @Autowired
    private MessageClient              messageClient;

    @Autowired
    private SmsClient                  smsClient;

    @Autowired
    protected StoreService             storeService;

    @Autowired
    protected MerchantWealthService    merchantWealthService;

    @Autowired
    protected MerchantOrderFormService merchantOrderFormService;

    @Autowired
    private MerchantEvaluationService  merchantEvaluationService;

    @Autowired
    private SexpressService            sexpressService;

    @Autowired
    private SexpressDistrictService    sexpressDistrictService;

    @Override
    public QMerchant getMerchant(long merchantId) {

        Merchant merchant = merchantService.get(merchantId);
        return new MerchantEntity(merchant);
    }

    @Override
    public List<QStore> listStoreByMerchant(long merchantId) {

        List<QStore> result = new ArrayList<QStore>();
        List<Store> list = storeService.listByMerchant(merchantId);
        for (Store store : list) {
            StoreEntity storeEntity = new StoreEntity();
            storeEntity.setId(store.getId());
            storeEntity.setName(store.getName());
            storeEntity.setAddress(store.getProvince() + store.getCity() + store.getDistrict() + store.getAddress());
            storeEntity.setPhone(store.getPhone());
            storeEntity.setMerchantId(merchantId);
            if (store.getParentId() == Integer.valueOf(-1)) {
                storeEntity.setRoot(true);
            } else {
                storeEntity.setRoot(false);
            }
            result.add(storeEntity);
        }
        return result;
    }

    @Override
    public QStore getStore(long storeId) {

        Store store = storeService.get(storeId);
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setId(store.getId());
        storeEntity.setName(store.getName());
        storeEntity.setAddress(store.getProvince() + store.getCity() + store.getDistrict() + store.getAddress());
        storeEntity.setPhone(store.getPhone());
        storeEntity.setMerchantId(store.getMerchantId());
        storeEntity.setSmsMobile(store.getMobile());
        storeEntity.setLatitude(store.getLatitude());
        storeEntity.setLongitude(store.getLongitude());
        if (store.getParentId() == Integer.valueOf(-1)) {
            storeEntity.setRoot(true);
        } else {
            storeEntity.setRoot(false);
        }
        return storeEntity;
    }

    @Override
    public boolean sendMsgToMerchant(long merchantId, SellerMessageType type, String title, String content) {

        return messageClient.sendMsg(TypeEnum.MERCHANT_MESSAGE_CODE, type.getKey(), merchantId, title, content);
    }

    @Override
    public boolean sendMsgToStore(long storeId, SellerMessageType type, String title, String content) {

        return messageClient.sendMsg(TypeEnum.STORE_MESSAGE_CODE, type.getKey(), storeId, title, content);
    }

    @Override
    public boolean sendSmsToStore(long storeId, String code, Map<String, String> map) {

        Store store = storeService.get(storeId);
        Merchant merchant = merchantService.get(store.getMerchantId());
        if (NotifyType.Yes.getKey() == merchant.getNotify() && StringUtils.isNotEmpty(store.getMobile())) {
            smsClient.send(code, store.getMobile(), map);
            return true;
        }
        return false;
    }

    @Override
    public boolean sendSmsToMerchant(long merchantId, String code, Map<String, String> map) {

        Merchant merchant = merchantService.get(merchantId);
        if (NotifyType.Yes.getKey() == merchant.getNotify() && StringUtils.isNotEmpty(merchant.getReceiveMobile())) {
            smsClient.send(code, merchant.getReceiveMobile(), map);
            return true;
        }
        return false;
    }

    @Override
    public double calculatePostage(String expressCode, long merchantId, double weight, String city) {

        Sexpress sexpress = sexpressService.getByCode(expressCode, merchantId);
        AssertUtil.assertNotNull(sexpress, "商家不支持该快递公司.");
        if (sexpress.getType() == SexpressType.Free.getKey()) {// 包邮
            return 0;
        } else if (sexpress.getType() == SexpressType.Fixed.getKey()) {// 固定邮费
            return sexpress.getFixedPrice();
        } else { // 区域收费
            double totalSum = 0.0;
            if (weight <= sexpress.getFirstWeight()) {
                totalSum = sexpress.getFirstPrice();
            } else {
                totalSum = sexpress.getFirstPrice() + Math.ceil((weight - sexpress.getFirstWeight()) / sexpress.getContinuedWeight()) * sexpress.getContinuedPrice();
            }
            if (StringUtils.isNotEmpty(city)) {
                List<SexpressDistrict> sexpressDistrictList = sexpressDistrictService.listByExpressId(sexpress.getId());
                for (SexpressDistrict sexpressDistrict : sexpressDistrictList) {
                    if (sexpressDistrict.getCity().equals(city)) {
                        //
                        if (weight <= sexpress.getFirstWeight()) {
                            totalSum = sexpressDistrict.getFirstPrice();
                        } else {
                            totalSum = sexpressDistrict.getFirstPrice() + Math.ceil((weight - sexpress.getFirstWeight()) / sexpress.getContinuedWeight()) * sexpressDistrict.getContinuedPrice();
                        }
                    }
                }
            }
            return totalSum;
        }
        // return 18;
    }

    @Override
    public List<KeyValueVO> listExpress(QMerchant merchant) {

        List<KeyValueVO> list = new ArrayList<KeyValueVO>();
        List<Sexpress> expressList = sexpressService.listByMerchant(merchant.getId());
        for (Sexpress sexpress : expressList) {
            KeyValueVO vo = new KeyValueVO();
            vo.setKey(sexpress.getCode());
            vo.setValue(sexpress.getName());
            list.add(vo);
        }
        // KeyValueVO keyValueVO1 = new KeyValueVO();
        // keyValueVO1.setKey("shunfeng");
        // keyValueVO1.setValue("顺丰");
        // KeyValueVO keyValueVO2 = new KeyValueVO();
        // keyValueVO2.setKey("yuantong");
        // keyValueVO2.setValue("圆通");
        // //
        // list.add(keyValueVO1);
        // list.add(keyValueVO2);
        return list;
    }

    @Override
    public String getExpressName(String expressCode) {

        // if ("shunfeng".equals(expressCode)) {
        // return "顺丰";
        // } else if ("yuantong".equals(expressCode)) {
        // return "圆通";
        // }
        return null;
    }

    @Override
    public boolean addMerchantEvaluation(long evaluationId, long merchantId, long merchandiseId) {

        MerchantEvaluation merchantEvaluation = new MerchantEvaluation();
        merchantEvaluation.setEvaluationId(evaluationId);
        merchantEvaluation.setMerchandiseId(merchandiseId);
        merchantEvaluation.setMerchantId(merchantId);
        merchantEvaluation.setEvaluationTime(new Date());
        return merchantEvaluationService.add(merchantEvaluation);
    }

    @Override
    public boolean addMerchantOrderForm(long merchantId, long orderId, long subOrderId, long storeId, int state, Date time) {

        MerchantOrderForm merchantOrderForm = new MerchantOrderForm();
        merchantOrderForm.setMerchantId(merchantId);
        merchantOrderForm.setOrderId(orderId);
        merchantOrderForm.setSubOrderId(subOrderId);
        merchantOrderForm.setState(state);
        merchantOrderForm.setTime(time);
        merchantOrderForm.setStoreId(storeId);
        return merchantOrderFormService.add(merchantOrderForm);
    }

    @Override
    public boolean updateOrderFormState(long merchantId, long subOrderId, int state) {

        MerchantOrderForm merchantOrderForm = merchantOrderFormService.getBySubOrder(merchantId, subOrderId);
        AssertUtil.assertNotNull(merchantOrderForm, "商家订单不存在." + subOrderId);
        merchantOrderForm.setState(state);
        return merchantOrderFormService.update(merchantOrderForm);
    }

    @Override
    public boolean updateOrderFormStore(long merchantId, long subOrderId, long storeId) {

        MerchantOrderForm merchantOrderForm = merchantOrderFormService.getBySubOrder(merchantId, subOrderId);
        AssertUtil.assertNotNull(merchantOrderForm, "商家订单不存在." + subOrderId);
        merchantOrderForm.setStoreId(storeId);
        // if (merchantOrderForm.getState() != MerchantOrderStateType.CONFIRM.getKey()) {
        // throw new SellerCenterException("只允许确定订单时修改门店!");
        // }
        return merchantOrderFormService.update(merchantOrderForm);
    }

    @Override
    public boolean deleteMerchantEvaluation(long evaluationId, long merchantId) {

        MerchantEvaluation merchantEvaluation = merchantEvaluationService.get(evaluationId, merchantId);
        if (merchantEvaluation == null) {
            return false;
        }
        return merchantEvaluationService.delete(evaluationId, merchantId);
    }

    @Override
    public boolean calculateMyWealth(long merchantId, WealthType type, double cash, boolean needProportion, String desc) {

        synchronized (new Long(merchantId)) {
            double wealth = cash;
            merchantWealthService.calculateWealth(merchantId, wealth, type.getKey(), desc);
            return true;
        }
    }

    @Override
    public QMerchantWealth getMyWealth(long merchantId) {

        MerchantWealth merchantWealth = merchantWealthService.getByMerchant(merchantId);
        if (merchantWealth == null) {
            return null;
        }
        MerchantWealthEntity merchantWealthEntity = new MerchantWealthEntity(merchantWealth);
        return merchantWealthEntity;
    }

    @Override
    public QMerchant getMerchant(String code) {

        Merchant merchant = merchantService.getByCode(code);
        if (merchant == null) {
            return null;
        }
        return new MerchantEntity(merchant);
    }
}
