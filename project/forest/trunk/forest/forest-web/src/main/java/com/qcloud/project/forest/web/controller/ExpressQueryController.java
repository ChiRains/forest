package com.qcloud.project.forest.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.web.vo.ExpressQueryVO;

@Controller
@RequestMapping(value = ExpressQueryController.DIR)
public class ExpressQueryController {

    public static final String DIR             = "/expressQuery";

    public static final String XML_Express_KEY = "express";

    /**
     * 快递查询
     * @param code
     * @param expressNum
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getExpressQuery(String code, String expressNum) {

        String result = sendPost("http://api.kuaidi100.com/api", "id=a960241fb0d1ec1d&com=" + code + "&nu=" + expressNum + "&show=0&order=desc");
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        Map<String, Object> jsonMap = Json.toMap(result);
        if (jsonMap.get("status").equals("1")) {
            Object[] objects = JSONArray.fromObject(jsonMap.get("data")).toArray();
            List<ExpressQueryVO> list = new ArrayList<ExpressQueryVO>();
            for (int i = 0, j = objects.length; i < j; i++) {
                list.add(Json.toObject(Json.toFormatJson(objects[i]), ExpressQueryVO.class));
            }
            for (ExpressQueryVO expressQueryVO : list) {
                expressQueryVO.setTime(DateUtil.date2String(DateUtil.str2Date(expressQueryVO.getTime()), "MM-dd HH:mm"));
            }
            frontAjaxView.addObject("resultStatus", 1);
            frontAjaxView.addObject("result", list);
        } else if (jsonMap.get("status").equals("2")) {
            frontAjaxView.addObject("resultStatus", 2);
            frontAjaxView.setMessage("接口异常");
        } else if (jsonMap.get("status").equals("0")) {
            frontAjaxView.addObject("resultStatus", 0);
            frontAjaxView.setMessage("暂时没有物流信息");
        }
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getAllExpress() {

        Xml xml = XmlFactory.get(XML_Express_KEY);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        if (xml != null) {
            List<XmlItem> messageList = xml.getItemList();
            for (XmlItem xmlItem : messageList) {
                map = new HashMap<String, Object>();
                map.put(xmlItem.getAttrMap().get("name"), xmlItem.getText());
                maps.add(map);
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", maps);
        return frontAjaxView;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
