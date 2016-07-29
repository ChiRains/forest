package com.qcloud.project.forest.web.controller.outside;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import com.qcloud.project.forest.web.vo.BMIVO;
import com.qcloud.project.forest.web.vo.RangeList;

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
        AssertUtil.assertTrue(app_id.equals(queryForm.getApp_id()), "appid不合法.");
        Map<String, Object> param = BeanUtils.transBean2Map(queryForm);
        Iterator<Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            if (entry.getValue() == null) {
                it.remove();
            }
        }
        AssertUtil.assertTrue(sign.equals(SignUtils.sign(secret, param)), "签名不正确.");
        String xmlText = client.requestToXml(queryForm);
        TextView view = new TextView(xmlText);
        logger.info(xmlText);
        return view;
    }

    public static void main(String[] args) {

        BMIVO vo = new BMIVO();
        vo.setBmi(222.0);
        List<RangeList> rangeDatas = new ArrayList<RangeList>();
        RangeList rangeList = new RangeList();
        rangeList.setAfterData(2);
        rangeList.setPreviousData(1);
        rangeDatas.add(rangeList);
        vo.setRangeData(rangeDatas);
        for (Entry entry : BeanUtils.transBean2Map(vo).entrySet()) {
            System.out.println(entry.getValue());
            if (entry.getValue() instanceof java.util.List) {
                for (Object obj : (List<?>) entry.getValue()) {
                    if (obj instanceof RangeList) {
                        System.out.println(((RangeList) obj).getAfterData());
                    }
                }
            }
        }
        System.out.println(BeanUtils.transBean2Map(vo));
    }
}
