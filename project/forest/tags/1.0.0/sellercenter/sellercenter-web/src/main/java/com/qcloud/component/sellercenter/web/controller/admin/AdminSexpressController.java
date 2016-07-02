package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Express;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.query.SexpressQuery;
import com.qcloud.component.sellercenter.service.SexpressDistrictService;
import com.qcloud.component.sellercenter.service.SexpressService;
import com.qcloud.component.sellercenter.web.form.AdminSexpressForm;
import com.qcloud.component.sellercenter.web.handler.SexpressDistrictHandler;
import com.qcloud.component.sellercenter.web.handler.SexpressHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressDistrictVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressFormVo;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminSexpressController.DIR)
public class AdminSexpressController {

    public static final String      DIR = "admin/sexpress";

    @Autowired
    private SexpressService         sexpressService;

    @Autowired
    private SexpressHandler         sexpressHandler;

    @Autowired
    private SexpressDistrictService sexpressDistrictService;

    @Autowired
    private SexpressDistrictHandler sexpressDistrictHandler;

    @Autowired
    private PublicdataClient        publicdataClient;

//    @Autowired
//    private AdminFilterService      adminFilterService;
//
//    @Autowired
//    private TokenClient             tokenClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, SexpressQuery query) {
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);            
        query.setMerchandId(merchant.getId());
        // System.out.println("商家id++++++++++++++++++++++++++++++++++++++\t"+merchandId);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Sexpress> page = sexpressService.page(query, start, PAGE_SIZE);
        List<AdminSexpressVO> list = sexpressHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-Sexpress-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-Sexpress-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(HttpServletRequest request, Sexpress sexpress, AdminSexpressForm adminSexpressForm,Integer extra) {       
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);               
        sexpress.setMerchandId(merchant.getId());
        if(extra==1){
           Express express= publicdataClient.getExpressByCode(sexpress.getCode());
           if(express!=null){
               throw new SellerCenterException("物流公司编码已存在."+express.getCode());
           }
        }
        Long id = sexpressService.add(sexpress);
        if (id != -1) {// 添加express成功
            List<AdminSexpressFormVo> list = adminSexpressForm.getList();
            for (AdminSexpressFormVo form : list) {
                if (form.getFirstWeight() != null && form.getContinuedWeight() != null && !form.getFirstWeight().equals("") && !form.getContinuedWeight().equals("")) {
                    SexpressDistrict expressDistrict = new SexpressDistrict();
                    expressDistrict.setFirstPrice(Double.valueOf(form.getFirstWeight()));
                    expressDistrict.setContinuedPrice(Double.valueOf(form.getContinuedWeight()));
                    expressDistrict.setExpressId(id);
                    expressDistrict.setProvince(form.getProvince());
                    expressDistrict.setCity(form.getCity());
                    sexpressDistrictService.add(expressDistrict);
                }
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Sexpress sexpress = sexpressService.get(id);
        AdminSexpressVO adminSexpressVO = sexpressHandler.toVO4Admin(sexpress);
        List<SexpressDistrict> districts = sexpressDistrictService.listByExpressId(id);
        List<AdminSexpressDistrictVO> adminSexpressDistrictVO = sexpressDistrictHandler.toVOList4Admin(districts);
        List<String> province = publicdataClient.listProvince();
        ModelAndView model = new ModelAndView("/admin/sellercenter-Sexpress-edit");
        model.addObject("sexpress", adminSexpressVO);
        model.addObject("expressDistrict", adminSexpressDistrictVO);
        model.addObject("province", province);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(HttpServletRequest request, Sexpress sexpress, AdminSexpressForm adminExpressForm) {
               
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        sexpress.setMerchandId(merchant.getId());
        sexpressService.update(sexpress);
        List<AdminSexpressFormVo> list = adminExpressForm.getList();
        List<SexpressDistrict> districts = sexpressDistrictService.listByExpressId(sexpress.getId());// 获取修改前的districts
        if (list != null) {
            // 修改快递地区
            for (AdminSexpressFormVo form : list) {
                if (form.getFirstWeight() != null && form.getContinuedWeight() != null && !form.getFirstWeight().equals("") && !form.getContinuedWeight().equals("")) {
                    if (form.getId() != 0) {// update
                        SexpressDistrict expressDistrict = new SexpressDistrict();
                        expressDistrict.setId(form.getId());
                        expressDistrict.setExpressId(sexpress.getId());
                        expressDistrict.setFirstPrice(Double.valueOf(form.getFirstWeight()));
                        expressDistrict.setContinuedPrice(Double.valueOf(form.getContinuedWeight()));
                        expressDistrict.setExpressId(sexpress.getId());
                        expressDistrict.setProvince(form.getProvince());
                        expressDistrict.setCity(form.getCity());
                        sexpressDistrictService.update(expressDistrict);
                    } else {// add
                        SexpressDistrict expressDistrict = new SexpressDistrict();
                        expressDistrict.setId(form.getId());
                        expressDistrict.setExpressId(sexpress.getId());
                        expressDistrict.setFirstPrice(Double.valueOf(form.getFirstWeight()));
                        expressDistrict.setContinuedPrice(Double.valueOf(form.getContinuedWeight()));
                        expressDistrict.setProvince(form.getProvince());
                        expressDistrict.setCity(form.getCity());
                        sexpressDistrictService.add(expressDistrict);
                    }
                }
            }
            // 删除去除的
            for (int i = 0; i < districts.size(); i++) {
                int sum = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getId() != null) {
                        if (list.get(j).getId() != districts.get(i).getId()) {
                            sum++;
                        }
                    } else {
                        sum++;
                    }
                }
                if (sum == list.size()) {
                    // System.out.println(districts.get(i).getId());
                    sexpressDistrictService.delete(districts.get(i).getId());
                }
            }
        } else {// 删除全部
            for (int i = 0; i < districts.size(); i++) {
                sexpressDistrictService.delete(districts.get(i).getId());
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        sexpressService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView enable(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Sexpress sexpress = sexpressService.get(id);
        AssertUtil.assertNotNull(sexpress, "数据不存在");
        sexpress.setEnable(1);
        sexpressService.update(sexpress);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView disable(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Sexpress sexpress = sexpressService.get(id);
        AssertUtil.assertNotNull(sexpress, "数据不存在");
        sexpress.setEnable(0);
        sexpressService.update(sexpress);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    // 管理员使用
    @RequestMapping
    @NoReferer
    public ModelAndView list4Admin(HttpServletRequest request, Integer pageNum, SexpressQuery query) {

        query.setMerchandId(-1L);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Sexpress> page = sexpressService.page(query, start, PAGE_SIZE);
        List<AdminSexpressVO> list = sexpressHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-Sexpress-list4Admin", DIR + "/list4Admin", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd4Admin() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-Sexpress-add4Admin");
        return model;
    }

    @RequestMapping
    public AceAjaxView add4Admin(HttpServletRequest request, Sexpress sexpress, AdminSexpressForm adminSexpressForm,Integer extra) {

        sexpress.setMerchandId(-1L);
        if(extra==1){
            Express express= publicdataClient.getExpressByCode(sexpress.getCode());
            if(express!=null){
                throw new SellerCenterException("物流公司编码已存在."+express.getCode());
            }
         }
        Long id = sexpressService.add(sexpress);
        if (id != -1) {// 添加express成功
            List<AdminSexpressFormVo> list = adminSexpressForm.getList();
            for (AdminSexpressFormVo form : list) {
                if (form.getFirstWeight() != null && form.getContinuedWeight() != null && !form.getFirstWeight().equals("") && !form.getContinuedWeight().equals("")) {
                    SexpressDistrict expressDistrict = new SexpressDistrict();
                    expressDistrict.setFirstPrice(Double.valueOf(form.getFirstWeight()));
                    expressDistrict.setContinuedPrice(Double.valueOf(form.getContinuedWeight()));
                    expressDistrict.setExpressId(id);
                    expressDistrict.setProvince(form.getProvince());
                    expressDistrict.setCity(form.getCity());
                    sexpressDistrictService.add(expressDistrict);
                }
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list4Admin");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit4Admin(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Sexpress sexpress = sexpressService.get(id);
        AdminSexpressVO adminSexpressVO = sexpressHandler.toVO4Admin(sexpress);
        List<SexpressDistrict> districts = sexpressDistrictService.listByExpressId(id);
        List<AdminSexpressDistrictVO> adminSexpressDistrictVO = sexpressDistrictHandler.toVOList4Admin(districts);
        List<String> province = publicdataClient.listProvince();
        ModelAndView model = new ModelAndView("/admin/sellercenter-Sexpress-edit4Admin");
        model.addObject("sexpress", adminSexpressVO);
        model.addObject("expressDistrict", adminSexpressDistrictVO);
        model.addObject("province", province);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit4Admin(HttpServletRequest request, Sexpress sexpress, AdminSexpressForm adminExpressForm) {

        sexpress.setMerchandId(-1L);
        sexpressService.update(sexpress);
        List<AdminSexpressFormVo> list = adminExpressForm.getList();
        List<SexpressDistrict> districts = sexpressDistrictService.listByExpressId(sexpress.getId());// 获取修改前的districts
        if (list != null) {
            // 修改快递地区
            for (AdminSexpressFormVo form : list) {
                if (form.getFirstWeight() != null && form.getContinuedWeight() != null && !form.getFirstWeight().equals("") && !form.getContinuedWeight().equals("")) {
                    if (form.getId() != 0) {// update
                        SexpressDistrict expressDistrict = new SexpressDistrict();
                        expressDistrict.setId(form.getId());
                        expressDistrict.setExpressId(sexpress.getId());
                        expressDistrict.setFirstPrice(Double.valueOf(form.getFirstWeight()));
                        expressDistrict.setContinuedPrice(Double.valueOf(form.getContinuedWeight()));
                        expressDistrict.setExpressId(sexpress.getId());
                        expressDistrict.setProvince(form.getProvince());
                        expressDistrict.setCity(form.getCity());
                        sexpressDistrictService.update(expressDistrict);
                    } else {// add
                        SexpressDistrict expressDistrict = new SexpressDistrict();
                        expressDistrict.setId(form.getId());
                        expressDistrict.setExpressId(sexpress.getId());
                        expressDistrict.setFirstPrice(Double.valueOf(form.getFirstWeight()));
                        expressDistrict.setContinuedPrice(Double.valueOf(form.getContinuedWeight()));
                        expressDistrict.setProvince(form.getProvince());
                        expressDistrict.setCity(form.getCity());
                        sexpressDistrictService.add(expressDistrict);
                    }
                }
            }
            // 删除去除的
            for (int i = 0; i < districts.size(); i++) {
                int sum = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getId() != null) {
                        if (list.get(j).getId() != districts.get(i).getId()) {
                            sum++;
                        }
                    } else {
                        sum++;
                    }
                }
                if (sum == list.size()) {
                    // System.out.println(districts.get(i).getId());
                    sexpressDistrictService.delete(districts.get(i).getId());
                }
            }
        } else {// 删除全部
            for (int i = 0; i < districts.size(); i++) {
                sexpressDistrictService.delete(districts.get(i).getId());
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        aceAjaxView.setUrl(DIR + "/list4Admin");
        return aceAjaxView;
    }
}
