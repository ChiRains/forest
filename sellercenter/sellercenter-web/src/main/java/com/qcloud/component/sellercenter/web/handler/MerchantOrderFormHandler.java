package com.qcloud.component.sellercenter.web.handler;

import java.util.List;

import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.web.vo.MerchantOrderFormVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantOrderFormVO;

public interface MerchantOrderFormHandler {

	List<MerchantOrderFormVO> toVOList(List<MerchantOrderForm> list);

	MerchantOrderFormVO toVO(MerchantOrderForm merchantOrderForm);

	List<AdminMerchantOrderFormVO> toVOList4Admin(List<MerchantOrderForm> list);

	AdminMerchantOrderFormVO toVO4Admin(MerchantOrderForm merchantOrderForm);
}
