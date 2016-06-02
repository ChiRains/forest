package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.web.vo.MerchantMerchandiseClassifyVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantMerchandiseClassifyVO;

public interface MerchantMerchandiseClassifyHandler {

    List<AdminMerchantMerchandiseClassifyVO> toVOList4Admin(List<MerchantMerchandiseClassify> list);

    AdminMerchantMerchandiseClassifyVO toVO4Admin(MerchantMerchandiseClassify merchantMerchandiseClassify);

    List<MerchantMerchandiseClassifyVO> classifyToVoList(List<MerchantMerchandiseClassify> list, List<Classify> classifyList);
}
