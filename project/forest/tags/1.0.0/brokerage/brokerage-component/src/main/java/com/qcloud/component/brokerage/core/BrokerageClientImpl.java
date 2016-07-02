package com.qcloud.component.brokerage.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.brokerage.BrokerageClient;
import com.qcloud.component.brokerage.BrokerageDataSourceElement;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.service.DataPoolService;

@Service
public class BrokerageClientImpl implements BrokerageClient {

    @Autowired
    private DataPoolService dataPoolService;

    @Override
    public boolean addSourceDataPool(BrokerageDataSourceElement ds) {

        if (ds == null) {
            return false;
        }
        if (new Double(ds.getCash() * 100).longValue() <= 0) {
            return false;
        }
        DataPool dataPool = new DataPool();
        dataPool.setCash(ds.getCash());
        dataPool.setDiscount(ds.getDiscount());
        dataPool.setFormulaId(ds.getFormulaId());
        dataPool.setImage(ds.getImage());
        dataPool.setMerchantId(ds.getMerchantId());
        dataPool.setName(ds.getTitle());
        dataPool.setNumber(ds.getNumber());
        dataPool.setOrderTime(ds.getOrderTime());
        dataPool.setPurchase(ds.getPurchase());
        dataPool.setSourceDateId(ds.getSourceDataId());
        dataPool.setType(ds.getDateType());
        dataPool.setUserId(ds.getUserId());
        return dataPoolService.add(dataPool);
    }
}