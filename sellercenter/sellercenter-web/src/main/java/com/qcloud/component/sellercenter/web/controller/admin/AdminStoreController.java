package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.util.TreeUtils;
import com.qcloud.component.publicdata.util.TreeUtils.TreeModel;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreQuery;
import com.qcloud.component.sellercenter.service.MemberService;
import com.qcloud.component.sellercenter.service.StoreMemberService;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.component.sellercenter.web.handler.StoreHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminStoreController.DIR)
public class AdminStoreController {

    public static final String DIR = "admin/store";

    @Autowired
    private StoreService       storeService;

    @Autowired
    private StoreHandler       storeHandler;

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private StoreMemberService storeMemberService;

    @Autowired
    private MemberService      memberService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, StoreQuery query) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        Page<Store> page = storeService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminStoreVO> list = storeHandler.toVOList4Admin(page.getData());
        String param = "name=" + StringUtil.nullToEmpty(query.getName());
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-Store-list", DIR + "/list?" + param, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        List<Store> storeList = storeService.listByMerchant(merchant.getId());
        List<TreeModel> treeModelList = new ArrayList<TreeModel>();
        for (Store store : storeList) {
            treeModelList.add(store);
        }
        List<KeyValueVO> storeKVList = TreeUtils.exchangeObj(treeModelList, -1L, "");
        List<String> provinceList = publicdataClient.listProvince();
        List<KeyValueVO> voList = publicdataClient.exchageStr(provinceList, null, null);
        ModelAndView model = new ModelAndView("/admin/sellercenter-Store-add");
        model.addObject("provinceList", voList);
        model.addObject("storeKVList", storeKVList);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(HttpServletRequest request, Store store) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        store.setMerchantId(merchant.getId());
        storeService.add(store);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Store store = storeService.get(id);
        List<Store> storeList = storeService.listByMerchant(store.getMerchantId());
        List<TreeModel> treeModelList = new ArrayList<TreeModel>();
        for (Store s : storeList) {
            treeModelList.add(s);
        }
        List<TreeModel> filterList = new ArrayList<TreeModel>();
        for (TreeModel treeModel : treeModelList) {
            boolean result = TreeUtils.isParent(treeModelList, treeModel, store == null ? -1 : store.getId());
            if (!result) {
                filterList.add(treeModel);
            }
        }
        List<KeyValueVO> storeKVList = TreeUtils.exchangeObj(filterList, store.getParentId(), "selected");
        AdminStoreVO adminStoreVO = storeHandler.toVO4Admin(store);
        ModelAndView model = new ModelAndView("/admin/sellercenter-Store-edit");
        List<String> list = publicdataClient.listProvince();
        List<KeyValueVO> voList = publicdataClient.exchageStr(list, store.getProvince(), "selected");
        model.addObject("provinceList", voList);
        List<String> cityList = publicdataClient.listCity(store.getProvince());
        List<KeyValueVO> cityVOList = publicdataClient.exchageStr(cityList, store.getCity(), "selected");
        model.addObject("cityList", cityVOList);
        List<String> districtList = publicdataClient.listDistrict(store.getCity());
        List<KeyValueVO> districtVOList = publicdataClient.exchageStr(districtList, store.getDistrict(), "selected");
        model.addObject("districtList", districtVOList);
        model.addObject("store", adminStoreVO);
        model.addObject("storeKVList", storeKVList);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Store store) {

        storeService.update(store);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        storeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView enable(Long id, Integer value) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        AssertUtil.assertNotNull(value, "value不能为空");
        Store store = storeService.get(id);
        AssertUtil.assertNotNull(store, "门店不存在.");
        String message = null;
        HashMap map = new HashMap();
        map.put("storeId", id);
        List<StoreMember> storeMemberList = storeMemberService.list(map);
        if (EnableType.DISABLE.getKey() == value) {
            List<Store> storeList = storeService.listByParentId(store.getId());
            if (storeList.size() > 0) {
                throw new SellerCenterException(store.getName() + "有子门店.");
            }
            // 门店成员全部禁止
            for (StoreMember storeMember : storeMemberList) {
                Member member = memberService.get(storeMember.getMemberId());
                AssertUtil.assertNotNull(member, "成员不存在,请检查数据." + storeMember.getMemberId());
                member.setEnable(EnableType.DISABLE.getKey());
                memberService.update(member);
            }
            store.setEnable(EnableType.DISABLE.getKey());
            message = "禁用成功";
        } else if (EnableType.ENABLE.getKey() == value) {
            Long parentId = store.getParentId();
            if (parentId > 0) {
                Store parentStore = storeService.get(parentId);
                AssertUtil.assertNotNull(parentStore, "门店不存在." + parentId);
                if (parentStore.getEnable() == EnableType.DISABLE.getKey()) {
                    throw new SellerCenterException("请先启用上级门店." + parentStore.getName());
                }
            }
            // 门店成员全部启用
            for (StoreMember storeMember : storeMemberList) {
                Member member = memberService.get(storeMember.getMemberId());
                AssertUtil.assertNotNull(member, "成员不存在,请检查数据." + storeMember.getMemberId());
                member.setEnable(EnableType.ENABLE.getKey());
                memberService.update(member);
            }
            store.setEnable(EnableType.ENABLE.getKey());
            message = "启用成功";
        } else {
            throw new SellerCenterException("启、禁用状态不正确.");
        }
        storeService.update(store);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage(message);
        aceAjaxView.setUrl("admin/store/list");
        return aceAjaxView;
    }
}
