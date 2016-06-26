package com.qcloud.component.marketing.web.controller.admin;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.OutdatedCommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;
import com.qcloud.component.goods.service.MerchandiseMarketingService;
import com.qcloud.component.marketing.model.key.TypeEnum;
import com.qcloud.component.marketing.web.form.MultipleSortForm;
import com.qcloud.component.marketing.web.form.PointExchangeMerchandiseForm;
import com.qcloud.component.marketing.web.handler.MerchandiseMarketingHandler;
import com.qcloud.component.marketing.web.vo.admin.AdminMerchandiseMarketingVO;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;

@Controller
@RequestMapping(value = "/" + AdminPointExchangeMerchandiseController.DIR)
public class AdminPointExchangeMerchandiseController {

    public static final String            DIR = "admin/pointExchange";

    @Autowired
    private MerchandiseMarketingHandler   merchandiseMarketingHandler;

    @Autowired
    private MerchandiseMarketingService   merchandiseMarketingService;

    @Autowired
    private OutdatedCommoditycenterClient outdatedCommoditycenterClient;

    @Autowired
    private CommoditycenterClient         commoditycenterClient;

    @Autowired
    private ParameterClient               parameterClient;

    @RequestMapping
    public AceAjaxView addExchangeMerchandise(PointExchangeMerchandiseForm form) {

        List<Long> unifiedMerchandiseIds = form.getUnifiedMerchandiseIds(); // 存放unifiedIds[] 锁定库存的
        List<Long> merchandiseItemsIds = form.getMerchandiseItemsIds(); // 存放itemsIds[]
        List<Long> merchantIds = form.getMerchantIds(); // 存放merchantIds []
        List<Integer> orderNums = form.getOrderNums();
        List<Double> purchase = form.getPurchase();
        List<Double> discount = form.getDiscount();
        List<Double> price = form.getPrice();
        List<Integer> stock = form.getStock();
        List<Integer> oldStock = form.getOldStock();
        AssertUtil.assertNotNull(merchandiseItemsIds, "商品列表不能为空");
        // 判断库存量
        for (int i = 0; i < stock.size(); i++) {
            AssertUtil.assertTrue(stock.get(i) <= oldStock.get(i), "超出库存量");
            AssertUtil.assertTrue(discount.get(i) >= 0, "价格不正确");
        }
        for (int i = 0; i < merchandiseItemsIds.size(); i++) {
            if (exist(merchandiseItemsIds.get(i))) {
                continue;
            }
            // 获取merchandise：取到sysCode 和 name
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseIds.get(i));
            Long merchandiseId = qUnifiedMerchandise.getList().get(0).getMerchandiseId();
            Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(merchandiseId);
            // 插入unifiedMerchandise表
            UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
            unifiedMerchandise.setMerchantId(merchantIds.get(i));
            unifiedMerchandise.setType(3);
            Long UID = outdatedCommoditycenterClient.returnUnifiedMerchandiseId(unifiedMerchandise);
            // 插入marketing-merchandise表
            MerchandiseMarketing merchandiseMarketing = new MerchandiseMarketing();
            merchandiseMarketing.setName(merchandise.getName());
            merchandiseMarketing.setSysCode(merchandise.getSysCode());
            merchandiseMarketing.setUnifiedMerchandiseId(UID);
            merchandiseMarketing.setOrder(orderNums.get(i));
            merchandiseMarketing.setDiscount(discount.get(i));
            merchandiseMarketing.setPrice(price.get(i));
            merchandiseMarketing.setPurchase(purchase.get(i));
            merchandiseMarketing.setMerchandiseItemId(merchandiseItemsIds.get(i));
            merchandiseMarketing.setStock(stock.get(i));
            merchandiseMarketing.setSence(TypeEnum.SenceType.POINTEXCHANGE.getKey());
            merchandiseMarketing.setActivityId((long) TypeEnum.SenceType.POINTEXCHANGE.getKey());
            merchandiseMarketing.setCurrencyType(TypeEnum.CurrencyType.POINT.getKey());
            merchandiseMarketing.setUpdateTime(new Date());
            outdatedCommoditycenterClient.addMerchandisMarketing(merchandiseMarketing);
            // 锁定库存
            commoditycenterClient.lockOnlineStock(unifiedMerchandiseIds.get(i), stock.get(i));
        }
        AceAjaxView view = new AceAjaxView();
        view.setMessage("添加成功");
        view.setUrl(DIR + "/exchangeList");
        return view;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView view = new ModelAndView("/admin/marketing-PointExchange-add");
        String bit = String.valueOf(parameterClient.get("point-pointConfig"));
        view.addObject("bit", bit);
        MerchandiseMarketingQuery query = new MerchandiseMarketingQuery();
        query.setActivityId((long) TypeEnum.SenceType.POINTEXCHANGE.getKey());
        query.setSence(TypeEnum.SenceType.POINTEXCHANGE.getKey());
        Page<MerchandiseMarketing> pages = outdatedCommoditycenterClient.merchandiseMarketingList(query, 0, Integer.MAX_VALUE);
        String listIds = "";
        for (MerchandiseMarketing merchandise : pages.getData()) {
            listIds += merchandise.getMerchandiseItemId() + ",";
        }
        view.addObject("listIds", listIds);
        return view;
    }

    @RequestMapping
    public AcePagingView exchangeList(MerchandiseMarketingQuery query, PPage pPage) {

        query.setActivityId((long) TypeEnum.SenceType.POINTEXCHANGE.getKey());
        query.setSence(TypeEnum.SenceType.POINTEXCHANGE.getKey());
        Page<MerchandiseMarketing> pages = outdatedCommoditycenterClient.merchandiseMarketingList(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMerchandiseMarketingVO> voList = merchandiseMarketingHandler.toVOList4Admin(pages.getData());
        AcePagingView view = new AcePagingView("/admin/marketing-PointExchange-list", DIR + "/exchangeList?activityId=" + query.getActivityId() + "&sence=" + query.getSence() + "&name=" + query.getName(), pPage.getPageNum(), pPage.getPageSize(), pages.getCount());
        view.addObject("result", voList);
        view.addObject("query", query);
        return view;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        outdatedCommoditycenterClient.deleteMerchandiseOfMarketing(id);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("删除成功");
        return view;
    }

    boolean exist(Long merchandiseItemId) {

        MerchandiseMarketingQuery query = new MerchandiseMarketingQuery();
        query.setActivityId((long) TypeEnum.SenceType.POINTEXCHANGE.getKey());
        query.setSence(TypeEnum.SenceType.POINTEXCHANGE.getKey());
        Page<MerchandiseMarketing> pages = outdatedCommoditycenterClient.merchandiseMarketingList(query, 0, Integer.MAX_VALUE);
        for (MerchandiseMarketing merchandise : pages.getData()) {
            if (merchandise.getMerchandiseItemId() == merchandiseItemId) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping
    public AceAjaxView multipleSort(MultipleSortForm form, String queryStr) {

        List<MerchandiseMarketing> list = form.getList();
        for (MerchandiseMarketing temp : list) {
            //
            MerchandiseMarketing marketing = merchandiseMarketingService.get(temp.getId());
            if (marketing == null) {
                continue;
            }
            marketing.setOrder(temp.getOrder());
            merchandiseMarketingService.update(marketing);
        }
        AceAjaxView view = new AceAjaxView();
        view.setUrl(DIR + "/exchangeList?" + StringUtil.nullToEmpty(queryStr));
        return view;
    }
}
