package com.qcloud.project.forest.web.controller.outside;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.mvc.TextView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.project.forest.model.oms.QueryForm;
import com.qcloud.project.forest.util.SignUtils;
import com.qcloud.project.forest.web.outside.eximpl.OmsDispatcherClient;

// 提供给管易的接口
@Controller
@RequestMapping(value = OutsideToOmsController.DIR)
public class OutsideToOmsController {

    public static final String DIR    = "/";

    @Autowired
    public OmsDispatcherClient client;

    @Value("${forest.outside.oms.app_id}")
    private String             app_id;

    @Value("${forest.outside.oms.secret}")
    private String             secret;

    private Log                logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/omsToDispatcher", method = RequestMethod.GET)
    public TextView omsToDispatcher(QueryForm queryForm) {

        String sign = queryForm.getSign();
        queryForm.setSign(null);
        AssertUtil.assertTrue(app_id.equals(queryForm.getApp_id()), "appid不合法");
        Map<String, Object> param = BeanUtils.transBean2Map(queryForm);
        Iterator<Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            if (entry.getValue() == null) {
                it.remove();
            }
        }
        String sign1 = "sign1:" + sign;
        String sign2 = "sign2:" + SignUtils.sign(secret, param);
        System.err.println(param);
        System.err.println(sign1);
        System.err.println(sign2);
        TextView view = new TextView("测试访问成功.=================================================");
        client.request(queryForm);
        return view;
    }
}
