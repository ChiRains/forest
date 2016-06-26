package com.qcloud.component.goods.web.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.key.TypeEnum;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.goods.web.form.MerchandiseItemForm;
import com.qcloud.component.goods.web.handler.MerchandiseItemHandler;
import com.qcloud.component.goods.web.vo.MerchandiseItemExportVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseItemVO;
import com.qcloud.component.piratesship.web.util.ExcelUtils;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.util.ClassifyUtils;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.template.client.excel.ExcelClient;
import com.qcloud.component.template.client.instance.ClientFactory;
import com.qcloud.component.template.client.instance.OperatePVFactory;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = "/" + AdminMerchandiseItemController.DIR)
public class AdminMerchandiseItemController {

    public static final String     DIR = "admin/merchandiseItem";

    @Autowired
    private MerchandiseItemService merchandiseItemService;

    @Autowired
    private MerchandiseItemHandler merchandiseItemHandler;

    @Autowired
    private PublicdataClient       publicdataClient;

    @Autowired
    private CommoditycenterClient  commoditycenterClient;

    @RequestMapping
    public ModelAndView selectProductList(Integer pageNum, HttpServletRequest request, String kwd) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Map<String, Object> where = new HashMap<String, Object>();
        where.put("unifiedMerchandiseId_gt", 0);
        where.put("merchantId", merchant.getId());
        if (kwd != null && !kwd.trim().equals("")) {
            where.put("kwd_like", kwd);
        }
        Page<MerchandiseItem> page = merchandiseItemService.page(where, start, PAGE_SIZE);
        List<MerchandiseItem> list = page.getData();
        AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseItem-selectProduct-list", DIR + "/selectProductList.do" + (where.containsKey("kwd_like") ? "?kwd=" + kwd : ""), pageNum, PAGE_SIZE, page.getCount());
        List<QUnifiedMerchandise> qUnifiedMerchandiseList = new ArrayList<QUnifiedMerchandise>();
        for (MerchandiseItem merchandiseItem : list) {
            qUnifiedMerchandiseList.add(commoditycenterClient.getUnifiedMerchandise(merchandiseItem.getUnifiedMerchandiseId()));
        }
        pagingView.addObject("result", qUnifiedMerchandiseList);
        if (where.containsKey("kwd_like")) {
            pagingView.addObject("kwd", kwd);
        }
        return pagingView;
    }

    /**
     * 商品价格列表
     * @param pageNum
     * @param request
     * @param kwd
     * @return
     */
    @RequestMapping
    public ModelAndView list(HttpServletRequest request, Integer pageNum, MerchandiseItemQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        Page<MerchandiseItem> page = merchandiseItemService.page(query, start, PAGE_SIZE);
        List<AdminMerchandiseItemVO> list = merchandiseItemHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseItem-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/goods-MerchandiseItem-add");
        return model;
    }

    /**
     * 设置商品价格
     * @param form
     * @return
     */
    @RequestMapping
    public AceAjaxView add(MerchandiseItemForm form) {

        List<Long> merchandiseItemsIds = form.getMerchandiseItemsIds(); // 单品ID，存放itemsIds[]
        List<Double> purchases = form.getPurchase();
        List<Double> prices = form.getPrice();
        List<Double> discounts = form.getDiscount();
        AssertUtil.assertNotNull(merchandiseItemsIds, "商品列表不能为空");
        for (int i = 0; i < merchandiseItemsIds.size(); i++) {
            MerchandiseItem entity = merchandiseItemService.get(merchandiseItemsIds.get(i));
            entity.setPurchase(purchases.get(i));// 进货价
            entity.setPrice(prices.get(i));// 原价
            entity.setDiscount(discounts.get(i));// 折扣价
            entity.setUpdateTime(new Date());
            merchandiseItemService.update(entity);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchandiseItem merchandiseItem = merchandiseItemService.get(id);
        // AdminMerchandiseItemVO adminMerchandiseGroupbuysVO = merchandiseItemHandler.toVO4Admin(merchandiseItem);
        ModelAndView model = new ModelAndView("/admin/goods-MerchandiseItem-edit");
        model.addObject("merchandiseItem", merchandiseItem);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MerchandiseItem merchandiseItem) {

        AssertUtil.assertNotNull(merchandiseItem.getId(), "ID不能为空");
        MerchandiseItem entity = merchandiseItemService.get(merchandiseItem.getId());
        entity.setPurchase(merchandiseItem.getPurchase());// 进货价
        entity.setPrice(merchandiseItem.getPrice());// 原价
        entity.setDiscount(merchandiseItem.getDiscount());// 折扣价
        entity.setUpdateTime(new Date());
        merchandiseItemService.update(entity);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    // 8-31
    @RequestMapping
    public ModelAndView produce4CustomMall(MerchandiseItemQuery query, Integer pageNum) {

        if (query.getName() == null) {
            query.setName("");
        }
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Map<String, Object> where = new HashMap<String, Object>();
        where.put("name", query.getName());
        where.put("merchantId", query.getMerchantId() == 1 ? null : query.getMerchantId());
        Page<MerchandiseItem> items = merchandiseItemService.page(where, start, PAGE_SIZE);
        AcePagingView view = new AcePagingView("/admin/goods-MerchandiseItem-produce4CustomMall-list", DIR + "/produce4CustomMall.do?merchantId=" + query.getMerchantId() + "&name=" + query.getName(), pageNum, PAGE_SIZE, items.getCount());
        view.addObject("result", items.getData());
        view.addObject("name", query.getName());
        view.addObject("merchantId", query.getMerchantId());
        return view;
    }

    /**
     * 一准叉车列表
     * @param query
     * @param pageNum
     * @return
     */
    @RequestMapping
    public ModelAndView listForForklift(MerchandiseItemQuery query, Integer pageNum) {

        if (query.getName() == null) {
            query.setName("");
        }
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Map<String, Object> where = new HashMap<String, Object>();
        where.put("name", query.getName());
        // where.put("merchantId", query.getMerchantId() == 1 ? null : query.getMerchantId());
        Page<MerchandiseItem> items = merchandiseItemService.page(where, start, PAGE_SIZE);
        List<AdminMerchandiseItemVO> list = new ArrayList<AdminMerchandiseItemVO>();
        AdminMerchandiseItemVO adminMerchandiseItemVO = null;
        List<MerchandiseItem> MerchandiseItemList = items.getData();
        for (MerchandiseItem merchandiseItem : MerchandiseItemList) {
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseItem.getUnifiedMerchandiseId());
            adminMerchandiseItemVO = new AdminMerchandiseItemVO();
            adminMerchandiseItemVO.setUnifiedMerchandiseId(qUnifiedMerchandise.getId());
            adminMerchandiseItemVO.setName(qUnifiedMerchandise.getName());
            adminMerchandiseItemVO.setSpecifications(qUnifiedMerchandise.getSpecifications());
            adminMerchandiseItemVO.setImage(qUnifiedMerchandise.getImage());
            list.add(adminMerchandiseItemVO);
        }
        AcePagingView view = new AcePagingView("/admin/goods-MerchandiseItem-listForForklift-list", DIR + "/listForForklift.do?name=" + query.getName(), pageNum, PAGE_SIZE, items.getCount());
        // view.addObject("result", items.getData());
        view.addObject("result", list);
        view.addObject("name", query.getName());
        // view.addObject("merchantId", query.getMerchantId());
        return view;
    }

    //
    @RequestMapping
    public ModelAndView list4Select4Admin(MerchandiseItemQuery query, Integer pageNum, String listIds) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchandiseItem> items = merchandiseItemService.list4Select4Admin(query, start, PAGE_SIZE);
        List<AdminMerchandiseItemVO> voList = merchandiseItemHandler.toVOList4Admin(items.getData());
        // 过滤
        if (listIds != null && !listIds.equals("") && !listIds.equals("null")) {
            String[] ids = listIds.split(",");
            for (AdminMerchandiseItemVO adminMerchandiseItemVO : voList) {
                for (String id : ids) {
                    if (adminMerchandiseItemVO.getUnifiedMerchandiseId() == Long.parseLong(id)) {
                        adminMerchandiseItemVO.setIsCheck(1);
                    }
                }
            }
        }
        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE.getKey());
        List<KeyValueVO> mallCVOList = ClassifyUtils.exchangeObj(classifyList, query.getMallClassifyId() == null ? -1 : query.getMallClassifyId(), "selected");
        AcePagingView view = new AcePagingView("/admin/goods-MerchandiseItem-list4Select4Admin", DIR + "/list4Select4Admin.do?merchantId=" + (query.getMerchantId() == null ? 0 : query.getMerchantId()) + "&name=" + StringUtil.nullToEmpty(query.getName()) + "&listIds=" + listIds, pageNum, PAGE_SIZE, items.getCount());
        view.addObject("result", voList);
        view.addObject("query", query);
        view.addObject("listIds", listIds);
        view.addObject("mallClassifyList", mallCVOList);
        return view;
    }

    //
    @RequestMapping
    public ModelAndView listForAdmin(MerchandiseItemQuery query, Integer pageNum, String listIds) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchandiseItem> items = merchandiseItemService.list4Select4Admin(query, start, PAGE_SIZE);
        List<AdminMerchandiseItemVO> voList = merchandiseItemHandler.toVOList4Admin(items.getData());
        // 过滤
        if (listIds != null && !listIds.equals("") && !listIds.equals("null")) {
            String[] ids = listIds.split(",");
            for (AdminMerchandiseItemVO adminMerchandiseItemVO : voList) {
                for (String id : ids) {
                    if (adminMerchandiseItemVO.getId() == Long.parseLong(id)) {
                        adminMerchandiseItemVO.setIsCheck(1);
                    }
                }
            }
        }
        long classifyType = query.getClassifyType() > 0 ? query.getClassifyType() : ClassifyType.MERCHANDISE.getKey();
        List<Classify> classifyList = publicdataClient.listClassify(classifyType);
        List<KeyValueVO> mallCVOList = ClassifyUtils.exchangeObj(classifyList, query.getMallClassifyId() == null ? -1 : query.getMallClassifyId(), "selected");
        AcePagingView view = new AcePagingView("/admin/goods-MerchandiseItem-listForAdmin", DIR + "/listForAdmin.do?merchantId=" + (query.getMerchantId() == null ? 0 : query.getMerchantId()) + "&name=" + StringUtil.nullToEmpty(query.getName()) + "&listIds=" + listIds, pageNum, PAGE_SIZE, items.getCount());
        view.addObject("result", voList);
        view.addObject("query", query);
        view.addObject("listIds", listIds);
        view.addObject("mallClassifyList", mallCVOList);
        return view;
    }

    @RequestMapping
    public ModelAndView listForMerchant(HttpServletRequest request, MerchandiseItemQuery query, PPage pPage, String listIds) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        Page<MerchandiseItem> items = merchandiseItemService.list4Select4Admin(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMerchandiseItemVO> voList = merchandiseItemHandler.toVOList4Admin(items.getData());
        // 过滤
        if (listIds != null && !listIds.equals("") && !listIds.equals("null")) {
            String[] ids = listIds.split(",");
            for (AdminMerchandiseItemVO adminMerchandiseItemVO : voList) {
                for (String id : ids) {
                    if (adminMerchandiseItemVO.getId() == Long.parseLong(id)) {
                        adminMerchandiseItemVO.setIsCheck(1);
                    }
                }
            }
        }
        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE.getKey());
        List<KeyValueVO> mallCVOList = ClassifyUtils.exchangeObj(classifyList, query.getMallClassifyId() == null ? -1 : query.getMallClassifyId(), "selected");
        AcePagingView view = new AcePagingView("/admin/goods-MerchandiseItem-listForMerchant", DIR + "/listForMerchant.do?name=" + StringUtil.nullToEmpty(query.getName()) + "&listIds=" + listIds, pPage.getPageNum(), pPage.getPageSize(), items.getCount());
        view.addObject("result", voList);
        view.addObject("query", query);
        view.addObject("listIds", listIds);
        view.addObject("mallClassifyList", mallCVOList);
        return view;
    }

    //
    @RequestMapping
    public ModelAndView listForB2BEditOrder(HttpServletRequest request, MerchandiseItemQuery query, PPage pPage, Long userId) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        Page<MerchandiseItem> items = merchandiseItemService.list4Select4Admin(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMerchandiseItemVO> voList = merchandiseItemHandler.toVOList4Admin(items.getData());
        for (AdminMerchandiseItemVO adminMerchandiseItemVO : voList) {
            double vipDiscount = commoditycenterClient.getVipDiscount(userId, adminMerchandiseItemVO.getUnifiedMerchandiseId());
            adminMerchandiseItemVO.setDiscount(vipDiscount);
        }
        // 分类
        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE.getKey());
        List<KeyValueVO> mallCVOList = ClassifyUtils.exchangeObj(classifyList, query.getMallClassifyId() == null ? -1 : query.getMallClassifyId(), "selected");
        AcePagingView view = new AcePagingView("/admin/goods-MerchandiseItem-listForB2BEditOrder", DIR + "/listForB2BEditOrder.do?name=" + StringUtil.nullToEmpty(query.getName()), pPage.getPageNum(), pPage.getPageSize(), items.getCount());
        view.addObject("result", voList);
        view.addObject("query", query);
        view.addObject("mallClassifyList", mallCVOList);
        return view;
    }

    @RequestMapping
    public ModelAndView toImport() {

        ModelAndView model = new ModelAndView("/admin/goods-MerchandiseItem-import");
        return model;
    }

    /**
     * 导入商品价格
     * @param resuest
     * @param response
     * @param driverReward
     */
    @RequestMapping
    public AceAjaxView merchandisePriceImport(MultipartHttpServletRequest multipartRequest) {

        List<MerchandiseItem> priceList = new ArrayList<MerchandiseItem>();
        try {
            Iterator<String> names = multipartRequest.getFileNames();
            while (names.hasNext()) {
                String name = (String) names.next();
                MultipartFile multipartFile = multipartRequest.getFile(name);
                List<String[]> list = ExcelUtils.read(multipartFile.getInputStream());
                for (int jndex = 1; jndex < list.size(); jndex++) {
                    String[] strings = list.get(jndex);
                    if (strings != null) {
                        MerchandiseItem item = new MerchandiseItem();
                        for (int index = 1; index < strings.length; index++) {
                            switch (index) {
                            case 1:
                                item.setId(Long.valueOf(strings[index].split("'")[1]));
                                break;
                            case 2:
                                item.setName(strings[index]);
                                break;
                            case 3:
                                item.setDiscount(Double.valueOf(strings[index]));
                                break;
                            default:
                                break;
                            }
                        }
                        priceList.add(item);
                    }
                }
            }
            //
            merchandiseItemService.importMerchandiseItem(priceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AceAjaxView view = new AceAjaxView();
        view.setMessage("操作成功.");
        view.setUrl(DIR + "/list");
        return view;
    }

    /**
     * 导出商品价格
     * @param resuest
     * @param response
     * @param driverReward
     */
    @SuppressWarnings("deprecation")
    @RequestMapping
    public void merchandisePriceExport(HttpServletRequest resuest, HttpServletResponse response, MerchandiseItem MerchandiseItem) {

        try {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=\"merchandisePrice.xls\"");
            List<MerchandiseItem> list = merchandiseItemService.listAll();
            List<MerchandiseItemExportVO> voList = new ArrayList<MerchandiseItemExportVO>();
            for (int i = 0; i < list.size(); i++) {
                MerchandiseItemExportVO voo = new MerchandiseItemExportVO();
                voo.setIndex(i + 1);
                voo.setDiscount(list.get(i).getDiscount());
                voo.setId("'"+list.get(i).getId());
                voo.setName(list.get(i).getName());
                voList.add(voo);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("result", voList);
            String fileDir = resuest.getRealPath(TypeEnum.EXCEL_TEMPLATE_DIR);
            ExcelClient excelClient = ClientFactory.getExcelClientInstance();
            excelClient.parse(OperatePVFactory.getTemplateFilePV(fileDir + "/merchandisePrice.xls"), OperatePVFactory.getObjectValuePV(map), OperatePVFactory.getROOSPV(response.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
