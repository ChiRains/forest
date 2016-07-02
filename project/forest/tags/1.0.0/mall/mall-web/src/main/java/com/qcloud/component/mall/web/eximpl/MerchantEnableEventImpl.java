package com.qcloud.component.mall.web.eximpl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.OutdatedCommoditycenterClient;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.key.TypeEnum;
import com.qcloud.component.sellercenter.MerchantEnableEvent;
import com.qcloud.component.sellercenter.QMerchant;

@Component
public class MerchantEnableEventImpl implements MerchantEnableEvent {

    @Autowired
    CommoditycenterClient commoditycenterClient;

    @Autowired
    OutdatedCommoditycenterClient outdatedCommoditycenterClient;
    
    Log                   logger = LogFactory.getLog(getClass());

    @Override
    public void doEvent(QMerchant merchant) {
        List<Merchandise> merchandiseList=outdatedCommoditycenterClient.getMerchandiseList(merchant.getId());
        for (Merchandise merchandise : merchandiseList) {
            if(merchandise.getState()==TypeEnum.MerchandiseStateType.ONLINE.getKey()){
                merchandise.setState(TypeEnum.MerchandiseStateType.OFFLINE.getKey());
                outdatedCommoditycenterClient.offline(merchandise.getId());
            }
            
        }
        logger.info(merchant.getId());
    }
}
