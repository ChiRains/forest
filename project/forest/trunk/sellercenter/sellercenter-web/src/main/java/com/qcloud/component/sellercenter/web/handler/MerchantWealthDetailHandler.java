package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantWealthDetailVO;

public interface MerchantWealthDetailHandler {

    List<AdminMerchantWealthDetailVO> toVOList4Admin(List<MerchantWealthDetail> list);

    AdminMerchantWealthDetailVO toVO4Admin(MerchantWealthDetail merchantWealthDetail);
}
