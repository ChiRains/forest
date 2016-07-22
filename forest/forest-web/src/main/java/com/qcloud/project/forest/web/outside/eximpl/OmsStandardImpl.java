package com.qcloud.project.forest.web.outside.eximpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.project.forest.service.oms.OmsDispatcherService;

@Component
public class OmsStandardImpl implements OmsStandard {

    @Autowired
    public OmsDispatcherService dispatcherService;

    public List<InvokeConfig>   invokeConfigs = new ArrayList<InvokeConfig>();

    @PostConstruct
    public void init() {

        Xml xml = XmlFactory.get("oms-dispacher-method");
        if (xml != null) {
            List<XmlItem> list = xml.getItemList();
            for (XmlItem xmlItem : list) {
                InvokeConfig invokeConfig = new InvokeConfig();
                List<Class<?>> paramTypes = new ArrayList<Class<?>>();
                String omsToMethod = xmlItem.getAttrMap().get("omsToMethod");
                String invoke = xmlItem.getAttrMap().get("invoke");
                String paramTypeStr = xmlItem.getAttrMap().get("paramType");
                invokeConfig.setOmsToMethod(omsToMethod);
                invokeConfig.setInvoke(invoke);
                for (String paramType : paramTypeStr.split(",")) {
                    try {
                        paramTypes.add(Class.forName(paramType));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                invokeConfig.setParamTypes(paramTypes);
                invokeConfigs.add(invokeConfig);
            }
        }
    }

    // 发货接口
    public final String LOGISTICS_OFFLINE_SEND = "logistics.offline.send";

    // 库存更新接口
    public final String ITEM_QUANTITY_UPDATE   = "item.quantity.update";

    private Log         logger                 = LogFactory.getLog(getClass());

    @Override
    public void handle(String method) {

        for (InvokeConfig invokeConfig : invokeConfigs) {
            if (invokeConfig.getOmsToMethod().equals(method)) {
                try {
                    Class<?>[] paramTypes = invokeConfig.getParamTypes().toArray(new Class<?>[invokeConfig.getParamTypes().size()]);
                    Method handle = dispatcherService.getClass().getMethod(invokeConfig.getInvoke(), paramTypes);
                    handle.invoke(dispatcherService, "张三");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
