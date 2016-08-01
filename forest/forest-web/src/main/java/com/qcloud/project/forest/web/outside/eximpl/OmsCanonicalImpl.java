package com.qcloud.project.forest.web.outside.eximpl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.project.forest.model.oms.QueryForm;
import com.qcloud.project.forest.model.oms.XmlResult;
import com.qcloud.project.forest.service.oms.OmsDispatcherService;
import com.qcloud.project.forest.util.OutsideUtil;

@Component
public class OmsCanonicalImpl implements OmsCanonical {

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

    @Override
    public String handleToXml(QueryForm queryForm) {

        String xml = "";
        for (InvokeConfig invokeConfig : invokeConfigs) {
            if (invokeConfig.getOmsToMethod().equals(queryForm.getMethod())) {
                try {
                    Class<?>[] paramTypes = invokeConfig.getParamTypes().toArray(new Class<?>[invokeConfig.getParamTypes().size()]);
                    Method handle = dispatcherService.getClass().getMethod(invokeConfig.getInvoke(), paramTypes);
                    Object obj = handle.invoke(dispatcherService, queryForm);
                    if (!Boolean.class.isInstance(obj)) {
                        xml = OutsideUtil.getReturnXml(new XmlResult(0, "成功"), obj);
                    } else {
                        xml = OutsideUtil.getReturnXml(new XmlResult(0, "成功"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return xml;
    }
}
