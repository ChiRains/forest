package com.qcloud.component.seckill.web.controller.admin;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.OutdatedCommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.marketing.model.key.TypeEnum;
import com.qcloud.component.publicdata.core.PublicdataClientImpl;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;
import com.qcloud.component.seckill.service.MerchandiseSeckillService;
import com.qcloud.component.seckill.service.ScreeningsService;
import com.qcloud.component.seckill.web.form.MerchandiseSeckillForm;
import com.qcloud.component.seckill.web.handler.MerchandiseSeckillHandler;
import com.qcloud.component.seckill.web.vo.admin.AdminMerchandiseSeckillVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchandiseSeckillController.DIR)
public class AdminMerchandiseSeckillController {

    public static final String            DIR = "admin/merchandiseSeckill";

    @Autowired
    private MerchandiseSeckillService     merchandiseSeckillService;

    @Autowired
    private MerchandiseSeckillHandler     merchandiseSeckillHandler;

    @Autowired
    private CommoditycenterClient         commoditycenterClient;

    @Autowired
    private OutdatedCommoditycenterClient outdatedCommoditycenterClient;

    @Autowired
    private PublicdataClientImpl          publicdataClientImpl;

    @Autowired
    private ScreeningsService             screeningsService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, MerchandiseSeckillQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchandiseSeckill> page = merchandiseSeckillService.page(query, start, PAGE_SIZE);
        List<AdminMerchandiseSeckillVO> list = merchandiseSeckillHandler.toVOList4Admin(page.getData());
        for (AdminMerchandiseSeckillVO adminMerchandiseSeckillVO : list) {
            // 统一商品名称
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(adminMerchandiseSeckillVO.getUnifiedMerchandiseId());
            // 商品分类名称
            Long merchandiseId = qUnifiedMerchandise.getList().get(0).getMerchandiseId();
            Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(merchandiseId);
            Classify merchandiseClassify = publicdataClientImpl.getClassify(merchandise.getMallClassifyId());
            adminMerchandiseSeckillVO.setMallClassifyName(merchandiseClassify.getName());
        }
        AcePagingView pagingView = new AcePagingView("/admin/seckill-MerchandiseSeckill-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(MerchandiseSeckillQuery query) {

        ModelAndView model = new ModelAndView("/admin/seckill-MerchandiseSeckill-add");
        model.addObject("query", query);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MerchandiseSeckillForm form, Long screeningsId) {

        List<Long> unifiedMerchandiseIds = form.getUnifiedMerchandiseIds(); // 统一商品ID，存放unifiedIds[] 锁定库存的
        List<Long> merchandiseItemsIds = form.getMerchandiseItemsIds(); // 单品ID，存放itemsIds[]
        List<Long> merchantIds = form.getMerchantIds(); // 商家ID，存放merchantIds []
        List<Integer> orderNums = form.getOrderNums();// 排序
        List<Integer> stock = form.getStock(); // 商品库存
        List<Double> seckillPrice = form.getSeckillPrice(); // 秒杀价格
        AssertUtil.assertNotNull(merchandiseItemsIds, "商品列表不能为空");
        for (int i = 0; i < merchandiseItemsIds.size(); i++) {
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseIds.get(i));
            Long merchandiseId = qUnifiedMerchandise.getList().get(0).getMerchandiseId();
            Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(merchandiseId);
            // 数据校验
            if (stock.get(i) == 0) { // 库存
                AssertUtil.assertTrue(false, "\"" + merchandise.getName() + "\"" + "库存应大于0");
            } else if (seckillPrice.get(i) == null || seckillPrice.get(i) == 0 || seckillPrice.get(i) > qUnifiedMerchandise.getDiscount()) { // 秒杀价
                AssertUtil.assertTrue(false, "\"" + merchandise.getName() + "\"" + "秒杀价应大于0，且必须小于统一商品的折扣价");
            } else if (screeningsId == null || screeningsId == 0) { // 秒杀场次
                AssertUtil.assertTrue(false, "\"" + merchandise.getName() + "\"" + "请选择秒杀场次");
            }
            // 保存列表中的数据比较：同一场次，商品不能重复
            for (int j = i + 1; j < merchandiseItemsIds.size(); j++) {
                if ((long) unifiedMerchandiseIds.get(i) == (long) unifiedMerchandiseIds.get(j)) {
                    AssertUtil.assertTrue(false, "\"" + merchandise.getName() + "\"	请勿重复添加");
                }
            }
            // 与数据库中的数据比较：同一场次，商品不能重复
            List<MerchandiseSeckill> seckillList = merchandiseSeckillService.listByScreeningsAndQUnifiedMerchandiseId(screeningsId, qUnifiedMerchandise.getId());
            if (seckillList != null && seckillList.size() > 0) {
                AssertUtil.assertTrue(false, "\"" + merchandise.getName() + "\"已存在，请修改");
            }
        }
        // 保存
        for (int i = 0; i < merchandiseItemsIds.size(); i++) {
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseIds.get(i));
            Long merchandiseId = qUnifiedMerchandise.getList().get(0).getMerchandiseId();
            Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(merchandiseId);
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
            merchandiseMarketing.setDiscount(qUnifiedMerchandise.getDiscount());
            merchandiseMarketing.setPrice(qUnifiedMerchandise.getPrice());
            merchandiseMarketing.setPurchase(qUnifiedMerchandise.getPurchase());
            merchandiseMarketing.setMerchandiseItemId(merchandiseItemsIds.get(i));
            merchandiseMarketing.setStock(stock.get(i)); // 库存
            merchandiseMarketing.setSence(TypeEnum.SenceType.GROUPBUYS.getKey());
            merchandiseMarketing.setActivityId((long) TypeEnum.SenceType.GROUPBUYS.getKey());
            merchandiseMarketing.setCurrencyType(TypeEnum.CurrencyType.RMB.getKey());
            merchandiseMarketing.setUpdateTime(new Date());
            outdatedCommoditycenterClient.addMerchandisMarketing(merchandiseMarketing);
            // 锁定库存
            commoditycenterClient.lockOnlineStock(unifiedMerchandiseIds.get(i), stock.get(i));
            MerchandiseSeckill merchandiseSeckill = new MerchandiseSeckill();
            merchandiseSeckill.setUnifiedMerchandiseId(UID);
            merchandiseSeckill.setMallClassifyId(merchandise.getMallClassifyId());
            merchandiseSeckill.setDiscountPrice(seckillPrice.get(i));
            merchandiseSeckill.setOriginalStock(stock.get(i));
            merchandiseSeckill.setSort(orderNums.get(i));
            merchandiseSeckill.setMerchandiseName(qUnifiedMerchandise.getName());
            merchandiseSeckill.setScreeningsId(screeningsId);
            merchandiseSeckill.setqUnifiedMerchandiseId(qUnifiedMerchandise.getId());
            merchandiseSeckillService.add(merchandiseSeckill);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?screeningsId=" + screeningsId);
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchandiseSeckill merchandiseSeckill = merchandiseSeckillService.get(id);
        AdminMerchandiseSeckillVO adminMerchandiseSeckillVO = merchandiseSeckillHandler.toVO4Admin(merchandiseSeckill);
        ModelAndView model = new ModelAndView("/admin/seckill-MerchandiseSeckill-edit");
        model.addObject("merchandiseSeckill", adminMerchandiseSeckillVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MerchandiseSeckill merchandiseSeckill) {

        merchandiseSeckillService.update(merchandiseSeckill);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id, Long screeningsId) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        AssertUtil.assertNotNull(screeningsId, "秒杀场次不能为空");
        // 已经开始的场次不能删除
        Screenings screenings = screeningsService.get(screeningsId);
        if (screenings.getBeginTime().getTime() <= new Date().getTime()) {
            AceAjaxView aceAjaxView = new AceAjaxView();
            aceAjaxView.setMessage("删除失败，此秒杀活动已经开始");
            aceAjaxView.setStatus(0);
            aceAjaxView.setUrl(DIR + "/list");
            return aceAjaxView;
        }
        MerchandiseSeckill merchandiseSeckill = merchandiseSeckillService.get(id);
        merchandiseSeckill.setEnable(2);
        merchandiseSeckillService.update(merchandiseSeckill);
        // merchandiseSeckillService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
