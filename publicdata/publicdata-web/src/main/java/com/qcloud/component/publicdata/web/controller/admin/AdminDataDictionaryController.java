package com.qcloud.component.publicdata.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicdata.model.DataDictionary;
import com.qcloud.component.publicdata.model.query.DataDictionaryQuery;
import com.qcloud.component.publicdata.service.DataDictionaryService;
import com.qcloud.component.publicdata.web.handler.DataDictionaryHandler;
import com.qcloud.component.publicdata.web.vo.admin.AdminDataDictionaryVO;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminDataDictionaryController.DIR)
public class AdminDataDictionaryController {

    public static final String    DIR                = "admin/dataDictionary";

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private DataDictionaryHandler dataDictionaryHandler;

    private static final String   XML_DICTIONARY_KEY = "publicdata-dictionary-key-list";

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, DataDictionaryQuery query) {

        Page<DataDictionary> page = dataDictionaryService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminDataDictionaryVO> list = dataDictionaryHandler.toVOList4Admin(page.getData());
        List<Map<String, Object>> maps = getAllDataDictionaryTypes();
        AcePagingView pagingView = new AcePagingView("/admin/publicdata-DataDictionary-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        pagingView.addObject("types", maps);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/publicdata-DataDictionary-add");
        List<Map<String, Object>> maps = getAllDataDictionaryTypes();
        model.addObject("types", maps);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(DataDictionary dataDictionary) {

        AssertUtil.assertNotNull(dataDictionary.getType(), "请选择类型");
        boolean flag = true;
        List<DataDictionary> list = dataDictionaryService.listAll(dataDictionary.getType());
        AceAjaxView aceAjaxView = new AceAjaxView();
        for (DataDictionary d : list) {
            if (d.getType().equals(dataDictionary.getType())) {
                if (d.getValue().equals(dataDictionary.getValue()) || d.getKey() == dataDictionary.getKey()) {
                    flag = false;
                }
            }
        }
        if (flag) {
            dataDictionaryService.add(dataDictionary);
            aceAjaxView.setMessage("添加成功");
        } else {
            aceAjaxView.setMessage("添加失败,键值对已存在." + dataDictionary.getType());
        }
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        DataDictionary dataDictionary = dataDictionaryService.get(id);
        AdminDataDictionaryVO adminDataDictionaryVO = dataDictionaryHandler.toVO4Admin(dataDictionary);
        ModelAndView model = new ModelAndView("/admin/publicdata-DataDictionary-edit");
        List<Map<String, Object>> maps = getAllDataDictionaryTypes();
        model.addObject("types", maps);
        model.addObject("dataDictionary", adminDataDictionaryVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(DataDictionary dataDictionary) {

        dataDictionaryService.update(dataDictionary);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        dataDictionaryService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    public List<Map<String, Object>> getAllDataDictionaryTypes() {

        Xml xml = XmlFactory.get(XML_DICTIONARY_KEY);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        if (xml != null) {
            List<XmlItem> messageList = xml.getItemList();
            for (XmlItem xmlItem : messageList) {
                map = new HashMap<String, Object>();
                map.put(xmlItem.getAttrMap().get("type"), xmlItem.getAttrMap().get("type") + "---" + xmlItem.getAttrMap().get("name"));
                maps.add(map);
            }
        }
        return maps;
    }
}
