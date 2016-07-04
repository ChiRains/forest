//package com.qcloud.component.sellercenter.web.controller.admin;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.servlet.ModelAndView;
//import com.qcloud.component.admin.AdminClient;
//import com.qcloud.component.admin.QAdmin;
//import com.qcloud.component.file.exception.FileException;
//import com.qcloud.component.personalcenter.PersonalcenterClient;
//import com.qcloud.component.personalcenter.QUser;
//import com.qcloud.component.piratesship.web.util.ExcelUtils;
//import com.qcloud.component.publicdata.ClassifyType;
//import com.qcloud.component.publicdata.KeyValueVO;
//import com.qcloud.component.publicdata.PublicdataClient;
//import com.qcloud.component.publicdata.SexType;
//import com.qcloud.component.publicdata.model.Classify;
//import com.qcloud.component.publicdata.util.ClassifyUtils;
//import com.qcloud.component.sellercenter.MerchantEnableEvent;
//import com.qcloud.component.sellercenter.QMerchant;
//import com.qcloud.component.sellercenter.SellercenterClient;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.Merchant;
//import com.qcloud.component.sellercenter.model.MerchantMember;
//import com.qcloud.component.sellercenter.model.Store;
//import com.qcloud.component.sellercenter.model.key.TypeEnum;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.CommodityAuditingType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.DistributionType;
//import com.qcloud.component.sellercenter.model.query.MerchantQuery;
//import com.qcloud.component.sellercenter.service.MerchantMemberService;
//import com.qcloud.component.sellercenter.service.MerchantService;
//import com.qcloud.component.sellercenter.service.StoreMemberService;
//import com.qcloud.component.sellercenter.service.StoreService;
//import com.qcloud.component.sellercenter.web.handler.MerchantHandler;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantVO;
//import com.qcloud.pirates.core.xml.Xml;
//import com.qcloud.pirates.core.xml.XmlFactory;
//import com.qcloud.pirates.core.xml.XmlItem;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.mvc.AceAjaxView;
//import com.qcloud.pirates.mvc.AcePagingView;
//import com.qcloud.pirates.mvc.RedirectView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.DateUtil;
//import com.qcloud.pirates.util.NumberUtil;
//import com.qcloud.pirates.util.RequestUtil;
//import com.qcloud.pirates.util.StringUtil;
//import com.qcloud.pirates.web.page.PPage;
//import com.qcloud.pirates.web.page.PageParameterUtil;
//import com.qcloud.pirates.web.page.PiratesParameterKey;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = "/" + AdminMerchantController.DIR)
//public class AdminMerchantController {
//
//    public static final String    DIR                = "admin/merchant";
//
//    @Autowired
//    private MerchantService       merchantService;
//
//    @Autowired
//    private MerchantHandler       merchantHandler;
//
//    @Autowired
//    private PublicdataClient      publicdataClient;
//
//    @Autowired
//    private StoreService          storeService;
//
//    @Autowired
//    private MerchantMemberService merchantMemberService;
//
//    @Autowired
//    private PersonalcenterClient  personalcenterClient;
//
//    @Autowired
//    private SellercenterClient    sellercenterClient;
//
//    @Autowired(required = false)
//    private MerchantEnableEvent   merchantEnableEvent;
//
//    // @Autowired
//    // private AdminFilterService adminFilterService;
//    //
//    // @Autowired
//    // private TokenClient tokenClient;
//    // @Autowired
//    // private AdminClient adminClient;
//    private String                merchant_admin_key = "sellercenter-merchant-admin-account";
//
//    private Log                   logger             = LogFactory.getLog(getClass());
//
//    @Autowired
//    private StoreMemberService    storeMemberService;
//
//    @RequestMapping
//    @NoReferer
//    public ModelAndView list(HttpServletRequest request, PPage pPage, MerchantQuery query) {
//
//        Xml xml = XmlFactory.get(merchant_admin_key);
//        AssertUtil.assertTrue(xml != null && !CollectionUtils.isEmpty(xml.getItemList()), "管理商家的所有管理员账号尚未配置." + merchant_admin_key);
//        XmlItem xmlItem = xml.getItemList().get(0);
//        String account = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("account")).trim();
//        AssertUtil.assertNotEmpty(account, "管理商家的所有管理员账号尚未配置account." + merchant_admin_key);
//        QAdmin admin = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_LOGIN_PARAMETER_KEY);
//        if (admin.getAccount().equals(account)) {
//            query.setAdmin(null);
//        } else {
//            query.setAdmin(admin.getAccount());
//        }
//        Page<Merchant> page = merchantService.page(query, pPage.getPageStart(), pPage.getPageSize());
//        List<AdminMerchantVO> list = merchantHandler.toVOList4Admin(page.getData());
//        String pageQueryStr = PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING);
//        String queryStr = PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING);
//        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-Merchant-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
//        pagingView.addObject("result", list);
//        pagingView.addObject("query", query);
//        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
//        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANT);
//        List<KeyValueVO> classifyListVOList = ClassifyUtils.exchangeObj(classifyList, query.getClassifyId(), "selected");
//        pagingView.addObject("classifyList", classifyListVOList);
//        return pagingView;
//    }
//
//    @RequestMapping
//    public ModelAndView details4Admin(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        Merchant merchant = merchantService.get(id);
//        AdminMerchantVO vo = merchantHandler.toVO4Admin(merchant);
//        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANT);
//        ModelAndView view = new ModelAndView("/admin/sellercenter-Merchant-details4Admin");
//        view.addObject("merchant", vo);
//        view.addObject("classifyList", classifyList);
//        return view;
//    }
//
//    @RequestMapping
//    public ModelAndView toAdd() {
//
//        ModelAndView model = new ModelAndView("/admin/sellercenter-Merchant-add");
//        List<String> list = publicdataClient.listProvince();
//        List<KeyValueVO> voList = publicdataClient.exchageStr(list, null, null);
//        model.addObject("provinceList", voList);
//        List<KeyValueVO> commodityAuditingTypeList = publicdataClient.exchageObj(CommodityAuditingType.values(), -1L, null);
//        List<KeyValueVO> distributionTypeList = publicdataClient.exchageObj(DistributionType.values(), -1L, null);
//        model.addObject("commodityAuditingTypeList", commodityAuditingTypeList);
//        model.addObject("distributionTypeList", distributionTypeList);
//        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANT);
//        List<KeyValueVO> classifyListVOList = ClassifyUtils.exchangeObj(classifyList, -1, "");
//        model.addObject("classifyList", classifyListVOList);
//        return model;
//    }
//
//    @RequestMapping
//    public AceAjaxView add(HttpServletRequest request, Merchant merchant) {
//
//        QAdmin admin = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_LOGIN_PARAMETER_KEY);
//        merchant.setAdmin(admin.getAccount());
//        merchantService.add(merchant);
//        Member member = new Member();
//        member.setNickname("商家默认账号.");
//        member.setUserId(-1L);
//        initMerchantOther(merchant, member);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("添加成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    private void initMerchantOther(Merchant merchant, Member member) {
//
//        Store store = new Store();
//        store.setName(merchant.getName() + "-总店");
//        store.setProvince(merchant.getProvince());
//        store.setCity(merchant.getCity());
//        store.setDistrict(merchant.getDistrict());
//        store.setAddress(merchant.getAddress());
//        store.setLatitude(merchant.getLatitude());
//        store.setLongitude(merchant.getLongitude());
//        store.setLogo(merchant.getLogo());
//        store.setParentId(-1L);
//        store.setMerchantId(merchant.getId());
//        storeService.add(store);
//        storeMemberService.add(member, merchant.getId(), store.getId());
//        MerchantMember merchantMember = new MerchantMember();
//        merchantMember.setMemberId(member.getId());
//        merchantMember.setMerchantId(merchant.getId());
//        merchantMemberService.add(merchantMember);
//    }
//
//    @RequestMapping
//    public ModelAndView toImport() {
//
//        ModelAndView model = new ModelAndView("/admin/sellercenter-Merchant-import");
//        return model;
//    }
//
//    @RequestMapping
//    public RedirectView importExcel(MultipartHttpServletRequest request) {
//
//        try {
//            Iterator<String> names = request.getFileNames();
//            while (names.hasNext()) {
//                String name = names.next();
//                MultipartFile multipartFile = request.getFile(name);
//                List<String[]> list = ExcelUtils.read(multipartFile.getInputStream());
//                for (int jndex = 1; jndex < list.size(); jndex++) {
//                    String[] strings = list.get(jndex);
//                    if (strings != null) {
//                        StringBuffer sb = new StringBuffer();
//                        sb.append(" merchant ");
//                        Merchant merchant = new Merchant();
//                        merchant.setRegistTime(new Date());
//                        merchant.setMerchantType(1);
//                        Member member = new Member();
//                        for (int index = 1; index < strings.length; index++) {
//                            sb.append(strings[index]).append(" ");
//                            switch (index) {
//                            case 1:
//                                merchant.setName(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 2:
//                                merchant.setFlagship(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 3:
//                                if ("是".equals(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    merchant.setCommodityAuditing(new Long(CommodityAuditingType.NEED.getKey()).intValue());
//                                } else {
//                                    merchant.setCommodityAuditing(new Long(CommodityAuditingType.UNNEED.getKey()).intValue());
//                                }
//                                break;
//                            case 4:
//                                merchant.setProvince(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 5:
//                                merchant.setCity(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 6:
//                                merchant.setDistrict(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 7:
//                                merchant.setAddress(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 8:
//                                if (StringUtils.isNotEmpty(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    merchant.setValidDate(DateUtil.str2Date(StringUtil.nullToEmpty(strings[index]).trim(), DateUtil.DATE_FORMAT_STRING));
//                                }
//                                break;
//                            case 9:
//                                if (StringUtils.isNotEmpty(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    merchant.setBuckle(Integer.parseInt(StringUtil.nullToEmpty(strings[index]).trim()));
//                                }
//                                break;
//                            case 10:
//                                if ("是".equals(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    merchant.setIsCertified(1);
//                                } else {
//                                    merchant.setIsCertified(2);
//                                }
//                                break;
//                            case 11:
//                                if ("是".equals(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    merchant.setIsSpecialService(1);
//                                } else {
//                                    merchant.setIsSpecialService(2);
//                                }
//                                break;
//                            case 12:
//                                if ("是".equals(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    merchant.setIsNoReason(1);
//                                } else {
//                                    merchant.setIsNoReason(2);
//                                }
//                                break;
//                            case 13:
//                                if ("是".equals(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    merchant.setIsExternalUrl(1);
//                                } else {
//                                    merchant.setIsExternalUrl(2);
//                                }
//                                break;
//                            case 14:
//                                member.setName(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 15:
//                                if ("男".equals(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    member.setSex(new Long(SexType.MALE.getKey()).intValue());
//                                } else if ("女".equals(StringUtil.nullToEmpty(strings[index]).trim())) {
//                                    member.setSex(new Long(SexType.FEMALE.getKey()).intValue());
//                                } else {
//                                    member.setSex(new Long(SexType.UNKNOW.getKey()).intValue());
//                                }
//                                break;
//                            case 16:
//                                member.setNickname(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 17:
//                                member.setMobile(StringUtil.nullToEmpty(strings[index]).trim());
//                                break;
//                            case 18:
//                                merchant.setAdmin(StringUtil.nullToEmpty(strings[index]).trim());
//                            }
//                        }
//                        int i = merchantService.countByName(merchant.getName());
//                        if (i > 0) {
//                            logger.info("exist merchant " + sb.toString());
//                        } else {
//                            logger.info("add merchant " + sb.toString());
//                            int isCertified = merchant.getIsCertified();
//                            int isExternalUrl = merchant.getIsExternalUrl();
//                            int isNoReason = merchant.getIsNoReason();
//                            int isSpecialService = merchant.getIsSpecialService();
//                            merchantService.add(merchant);
//                            merchant.setIsCertified(isCertified);
//                            merchant.setIsExternalUrl(isExternalUrl);
//                            merchant.setIsNoReason(isNoReason);
//                            merchant.setIsSpecialService(isSpecialService);
//                            merchantService.updateByAdmin(merchant);
//                            initMerchantOther(merchant, member);
//                        }
//                    }
//                }
//            }
//            return new RedirectView("/admin/index.do?#admin/merchant/list");
//        } catch (IOException e) {
//            throw new FileException("上传文件出错.", e);
//        }
//    }
//
//    // 禁用商家
//    @RequestMapping
//    public AceAjaxView disableMerchant(long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        merchantService.disableMerchant(id);
//        if (merchantEnableEvent != null) {
//            QMerchant merchant = sellercenterClient.getMerchant(id);
//            merchantEnableEvent.doEvent(merchant);
//        }
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("禁用商家成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    // 启用商家
//    @RequestMapping
//    public AceAjaxView enableMerchant(long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        merchantService.enableMerchant(id);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("启用商家成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public ModelAndView toAddMember4Admin(Long merchantId) {
//
//        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantMember-add4Admin");
//        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), -1, "selected");
//        model.addObject("sexTypeList", sexTypeList);
//        model.addObject("merchantId", merchantId);
//        return model;
//    }
//
//    @RequestMapping
//    public AceAjaxView addMember4Admin(HttpServletRequest request, Member member, Long merchantId) {
//
//        Merchant merchant = merchantService.get(merchantId);
//        AssertUtil.assertNotNull(merchant, "您尚未属于一家商家.");
//        merchantMemberService.add(member, merchantId);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("添加成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public ModelAndView toEdit(Long id, String queryStr) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        Merchant merchant = merchantService.get(id);
//        AdminMerchantVO adminMerchantVO = merchantHandler.toVO4Admin(merchant);
//        ModelAndView model = new ModelAndView("/admin/sellercenter-Merchant-edit");
//        model.addObject("merchant", adminMerchantVO);
//        List<String> list = publicdataClient.listProvince();
//        List<KeyValueVO> voList = publicdataClient.exchageStr(list, merchant.getProvince(), "selected");
//        model.addObject("provinceList", voList);
//        List<String> cityList = publicdataClient.listCity(merchant.getProvince());
//        List<KeyValueVO> cityVOList = publicdataClient.exchageStr(cityList, merchant.getCity(), "selected");
//        model.addObject("cityList", cityVOList);
//        List<String> districtList = publicdataClient.listDistrict(merchant.getCity());
//        List<KeyValueVO> districtVOList = publicdataClient.exchageStr(districtList, merchant.getDistrict(), "selected");
//        model.addObject("districtList", districtVOList);
//        List<KeyValueVO> commodityAuditingTypeList = publicdataClient.exchageObj(CommodityAuditingType.values(), merchant.getCommodityAuditing(), "selected");
//        List<KeyValueVO> distributionTypeList = publicdataClient.exchageObj(DistributionType.values(), merchant.getDistribution(), "selected");
//        model.addObject("commodityAuditingTypeList", commodityAuditingTypeList);
//        model.addObject("distributionTypeList", distributionTypeList);
//        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANT);
//        List<KeyValueVO> classifyListVOList = ClassifyUtils.exchangeObj(classifyList, merchant.getClassifyId(), "selected");
//        model.addObject("classifyList", classifyListVOList);
//        model.addObject("queryStr", queryStr);
//        return model;
//    }
//
//    @RequestMapping
//    public AceAjaxView edit(Merchant merchant, String queryStr) {
//
//        merchantService.updateByAdmin(merchant);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/list?" + queryStr);
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public ModelAndView toPerfect(HttpServletRequest request) {
//
//        QMerchant qMerchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
//        Merchant merchant = merchantService.get(qMerchant.getId());
//        AssertUtil.assertNotNull(merchant, "没有商家可管理");
//        AdminMerchantVO adminMerchantVO = merchantHandler.toVO4Admin(merchant);
//        ModelAndView model = new ModelAndView("/admin/sellercenter-Merchant-perfect");
//        model.addObject("merchant", adminMerchantVO);
//        List<String> list = publicdataClient.listProvince();
//        List<KeyValueVO> voList = publicdataClient.exchageStr(list, merchant.getProvince(), "selected");
//        model.addObject("provinceList", voList);
//        List<String> cityList = publicdataClient.listCity(merchant.getProvince());
//        List<KeyValueVO> cityVOList = publicdataClient.exchageStr(cityList, merchant.getCity(), "selected");
//        model.addObject("cityList", cityVOList);
//        List<String> districtList = publicdataClient.listDistrict(merchant.getCity());
//        List<KeyValueVO> districtVOList = publicdataClient.exchageStr(districtList, merchant.getDistrict(), "selected");
//        model.addObject("districtList", districtVOList);
//        String fileSize = publicdataClient.getImageInformationByCode("shangjiashangbiao");
//        model.addObject("fileSize", fileSize);
//        return model;
//    }
//
//    @RequestMapping
//    public AceAjaxView perfect(Merchant merchant) {
//
//        merchantService.updateByMerchant(merchant);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/toPerfect");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public AceAjaxView delete(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        merchantService.delete(id);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("删除成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public ModelAndView listNeedAudit(int pageNum, String keyword) {
//
//        pageNum = RequestUtil.getPageid(pageNum);
//        int start = NumberUtil.getPageStart(pageNum, 10);
//        Page<Merchant> page = merchantService.listNeedAudit(keyword, start, 10);
//        List<Merchant> merchantList = page.getData();
//        List<AdminMerchantVO> list = merchantHandler.toVOList4Admin(merchantList);
//        AcePagingView acePagingView = new AcePagingView("/admin/sellercenter-Merchant-needAudit", DIR + "/listNeedAudit.do", pageNum, 10, page.getCount());
//        acePagingView.addObject("result", list);
//        acePagingView.addObject("keyword", keyword);
//        return acePagingView;
//    }
//
//    @RequestMapping
//    public ModelAndView allMerchantlist(Integer pageNum, MerchantQuery query) {
//
//        final int PAGE_SIZE = 10;
//        pageNum = RequestUtil.getPageid(pageNum);
//        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//        Page<Merchant> page = merchantService.pageMerchant(query, start, PAGE_SIZE);
//        List<AdminMerchantVO> list = merchantHandler.toVOList4Admin(page.getData());
//        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantSort-allMerchant", DIR + "/list?name=" + StringUtil.nullToEmpty(query.getName()), pageNum, PAGE_SIZE, page.getCount());
//        pagingView.addObject("result", list);
//        pagingView.addObject("query", query);
//        return pagingView;
//    }
//
//    @RequestMapping
//    public ModelAndView selectUserList(Integer pageNum, String name) {
//
//        final int PAGE_SIZE = 10;
//        pageNum = RequestUtil.getPageid(pageNum);
//        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//        Page<QUser> userList = personalcenterClient.userPage(name, start, PAGE_SIZE);
//        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-Merchant-selectUserList", DIR + "/selectUserList?name=" + StringUtil.nullToEmpty(name), pageNum, PAGE_SIZE, userList.getCount());
//        pagingView.addObject("result", userList.getData());
//        pagingView.addObject("name", StringUtil.nullToEmpty(name));
//        return pagingView;
//    }
//
//    @RequestMapping
//    public AceAjaxView notifyMerchant(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        Merchant merchant = merchantService.get(id);
//        AssertUtil.assertNotNull(merchant, "商家不存在." + id);
//        merchantService.updateMerchantNotify(id, TypeEnum.NotifyType.Yes.getKey());
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("修改成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public AceAjaxView unNotifyMerchant(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        Merchant merchant = merchantService.get(id);
//        AssertUtil.assertNotNull(merchant, "商家不存在." + id);
//        merchantService.updateMerchantNotify(id, TypeEnum.NotifyType.No.getKey());
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("修改成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//}
