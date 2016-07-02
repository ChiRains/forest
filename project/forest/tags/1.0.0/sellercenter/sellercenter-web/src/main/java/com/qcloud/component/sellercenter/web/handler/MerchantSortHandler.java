package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.web.vo.MerchantSortVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantSortVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantVO;


public interface MerchantSortHandler {
    
    List<MerchantSortVO> toVOList(List<MerchantSort> list);
    
    MerchantSortVO toVO(MerchantSort merchantSort);
    
    List<AdminMerchantSortVO> toVOList4Admin(List<MerchantSort> list);
    
    AdminMerchantSortVO toVO4Admin(MerchantSort merchantSort);
}
