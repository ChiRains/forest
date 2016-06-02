package com.qcloud.component.warehouse;

public interface WarehouseClient {

    boolean reduce(Long merchantId, Long storeId, Long unifiedMerchandiseId, int number);
    
    int getStock(Long merchantId, Long storeId, Long unifiedMerchandiseId);
}