package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantWealthVO;

public interface MerchantWealthHandler {

    List<AdminMerchantWealthVO> toVOList4Admin(List<MerchantWealth> list);

    AdminMerchantWealthVO toVO4Admin(MerchantWealth merchantWealth);
}
