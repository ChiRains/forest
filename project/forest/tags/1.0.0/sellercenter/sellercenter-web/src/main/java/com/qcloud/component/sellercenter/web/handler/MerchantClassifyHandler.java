package com.qcloud.component.sellercenter.web.handler;
import java.util.List;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.web.vo.MerchantClassifyVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantClassifyVO;
public interface MerchantClassifyHandler {
    List<MerchantClassifyVO> toVOList(List<MerchantClassify> list);

    MerchantClassifyVO toVO(MerchantClassify merchantClassify);

    List<AdminMerchantClassifyVO> toVOList4Admin(List<MerchantClassify> list);

    List<AdminMerchantClassifyVO> toVOList4Admin(List<Classify> list, List<Long> keyList);

    AdminMerchantClassifyVO toVO4Admin(MerchantClassify merchantClassify);
}
