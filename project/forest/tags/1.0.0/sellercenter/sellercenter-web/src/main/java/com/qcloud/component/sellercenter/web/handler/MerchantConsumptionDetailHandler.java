package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantConsumptionDetailVO;

public interface MerchantConsumptionDetailHandler {

    List<AdminMerchantConsumptionDetailVO> toVOList4Admin(List<MerchantConsumptionDetail> list);

    AdminMerchantConsumptionDetailVO toVO4Admin(MerchantConsumptionDetail merchantConsumptionDetail);
}
