package com.qcloud.component.sellercenter.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellerMessageType;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.entity.MerchantEntity;
import com.qcloud.component.sellercenter.entity.StoreEntity;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.key.TypeEnum;
import com.qcloud.component.sellercenter.model.key.TypeEnum.NotifyType;
import com.qcloud.component.sellercenter.model.key.TypeEnum.SexpressType;
import com.qcloud.component.sellercenter.service.MerchantEvaluationService;
import com.qcloud.component.sellercenter.service.MerchantOrderFormService;
import com.qcloud.component.sellercenter.service.SexpressDistrictService;
import com.qcloud.component.sellercenter.service.SexpressService;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class SellercenterClientImpl implements SellercenterClient {

    @Autowired
    private MessageClient              messageClient;

    @Autowired
    private SmsClient                  smsClient;

    @Autowired
    protected MerchantOrderFormService merchantOrderFormService;

    @Autowired
    private MerchantEvaluationService  merchantEvaluationService;

    @Autowired
    private SexpressService            sexpressService;

    @Autowired
    private SexpressDistrictService    sexpressDistrictService;

    @Autowired
    private OrganizationClient         organizationClient;

    @Override
    public MerchantEntity getMerchant(long merchantId) {

        QDepartment department = organizationClient.getDepartment(merchantId);
        return new MerchantEntity(department);
    }

    @Override
    public List<QStore> listStoreByMerchant(long merchantId) {

        MerchantEntity merchant = getMerchant(merchantId);
        List<QStore> result = new ArrayList<QStore>();
        List<QDepartment> list = organizationClient.listDepartmantByParent(merchantId);
        for (QDepartment department : list) {
            StoreEntity storeEntity = new StoreEntity(merchant, department.getParent(), department);
            result.add(storeEntity);
        }
        return result;
    }

    @Override
    public StoreEntity getStore(long storeId) {

        QDepartment department = organizationClient.getDepartment(storeId);
        AssertUtil.assertNotNull(department, "门店不存在." + storeId);
        QDepartment merchant = getMerchantDepartment(department);
        AssertUtil.assertNotNull(merchant, "门店所属商家不存在." + storeId);
        StoreEntity storeEntity = new StoreEntity(new MerchantEntity(merchant), department.getParent(), department);
        return storeEntity;
    }

    private QDepartment getMerchantDepartment(QDepartment department) {

        while (true) {
            // 商家为2 门店为3
            if (department == null || department.getType() == 2) {
                return department;
            }
            department = department.getParent();
        }
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

        StoreEntity store = getStore(storeId);
        AssertUtil.assertNotNull(store, "门店不存在." + storeId);
        MerchantEntity merchant = store.getMerchant();
        if (merchant.isNotify() && StringUtils.isNotEmpty(store.getSmsMobile())) {
            smsClient.send(code, store.getSmsMobile(), map);
            return true;
        }
        return false;
    }

    @Override
    public boolean sendSmsToMerchant(long merchantId, String code, Map<String, String> map) {

        MerchantEntity merchant = getMerchant(merchantId);
        AssertUtil.assertNotNull(merchant, "商家不存在." + merchantId);
        if (merchant.isNotify() && StringUtils.isNotEmpty(merchant.getReceiveMobile())) {
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
    public boolean addMerchantEvaluation(long evaluationId, long merchantId, long merchandiseId, String content) {

        MerchantEvaluation merchantEvaluation = new MerchantEvaluation();
        merchantEvaluation.setEvaluationId(evaluationId);
        merchantEvaluation.setMerchandiseId(merchandiseId);
        merchantEvaluation.setMerchantId(merchantId);
        merchantEvaluation.setEvaluationTime(new Date());
        merchantEvaluation.setContent(content);
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
    public QMerchant getMerchantByCode(String code) {

        QDepartment department = organizationClient.getDepartmentByCode(code);
        return new MerchantEntity(department);
    }
}
