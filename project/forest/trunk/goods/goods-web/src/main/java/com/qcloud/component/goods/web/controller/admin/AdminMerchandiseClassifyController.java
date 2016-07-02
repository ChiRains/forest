package com.qcloud.component.goods.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.RedirectView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = AdminMerchandiseClassifyController.DIR)
public class AdminMerchandiseClassifyController {

    //
    public static final String DIR = "/admin/classifyMerchandise";

    @Autowired
    public PublicdataClient    publicdataClient;

    @Autowired
    public MerchandiseService  merchandiseService;

    // 只做一个入口,然后跳转,商城分类
    @RequestMapping
    @NoReferer
    public ModelAndView list() {

        return new RedirectView("/admin/classify4Type/list.do?type=" + ClassifyType.MERCHANT.getKey());
    }

    // 只做一个入口,然后跳转,商城分类
    @RequestMapping
    @NoReferer
    public ModelAndView listForMerchant() {

        return new RedirectView("/admin/classify4Types/listForMerchant.do?beanId=commoditycenter.MerchandiseClassifyHandler");
    }

    // 删除商品分类
    @RequestMapping
    public AceAjaxView deleteMallClassify(Long id) {

        AceAjaxView aceAjaxView = new AceAjaxView();
        AssertUtil.assertNotNull(id, "id不能为空");
        Classify classify = publicdataClient.getClassify(id);
        AssertUtil.assertNotNull(classify, "指定分类不存在");
        List<Classify> classifies = publicdataClient.listClassify(classify.getType());
        List<Classify> listChildrenIncludeMe = new ArrayList<Classify>();
        for (Classify c : classifies) {
            if (c.getBsid().startsWith(classify.getBsid())) {
                listChildrenIncludeMe.add(c);
            }
        }
        if (listChildrenIncludeMe.size() > 1) {
            aceAjaxView.setStatus(0);
            aceAjaxView.setMessage("删除失败,该分类包含" + (listChildrenIncludeMe.size() - 1) + "个子类");
        } else {
            int count = 0;
            for (Classify c : listChildrenIncludeMe) {
                count += merchandiseService.count4DeleteClassify(c.getId());
            }
            if (count > 0) {
                aceAjaxView.setStatus(0);
                aceAjaxView.setMessage("删除失败,该分类或其子类关联了" + count + "件商品");
            } else {
                publicdataClient.delete(id);
                aceAjaxView.setMessage("删除成功");
            }
        }
        return aceAjaxView;
    }
}
