package com.qcloud.project.forest.web.outside.eximpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.project.forest.service.oms.OmsDispatcherService;

@Service
public class OmsStandardImpl implements OmsStandard {

    // 发货接口
    public final String         LOGISTICS_OFFLINE_SEND = "logistics.offline.send";

    // 库存更新接口
    public final String         ITEM_QUANTITY_UPDATE   = "item.quantity.update";

    private Log                 logger                 = LogFactory.getLog(getClass());

    @Autowired
    public OmsDispatcherService dispatcherService;

    @Override
    public void handle(String method) {

        // 用JDK7不然×××××
//        switch (method) {
//        case LOGISTICS_OFFLINE_SEND: // 发货
//            //
//            dispatcherService.deliverOrder();
//            logger.info("发货接口调用." + method);
//            break;
//        case ITEM_QUANTITY_UPDATE:
//            logger.info("库存更新接口调用." + method);
//            break;
//        }
    }
}
