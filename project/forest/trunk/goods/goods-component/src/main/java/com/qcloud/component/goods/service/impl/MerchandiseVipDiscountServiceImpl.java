package com.qcloud.component.goods.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.dao.MerchandiseVipDiscountDao;
import com.qcloud.component.goods.dao.MerchandiseVipDiscountHistoryDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountQuery;
import com.qcloud.component.goods.service.MerchandiseVipDiscountDateService;
import com.qcloud.component.goods.service.MerchandiseVipDiscountService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MerchandiseVipDiscountServiceImpl implements MerchandiseVipDiscountService {

    @Autowired
    private MerchandiseVipDiscountDao         merchandiseVipDiscountDao;

    @Autowired
    private MerchandiseVipDiscountHistoryDao  merchandiseVipDiscountHistoryDao;

    @Autowired
    private AutoIdGenerator                   autoIdGenerator;

    @Autowired
    private PublicdataClient                  publicdataClient;

    @Autowired
    private MerchandiseVipDiscountDateService merchandiseVipDiscountDateService;

    private static final String               ID_KEY = "goods_merchandise_vip_discount";

    @Autowired
    private UnifiedMerchandiseService         unifiedMerchandiseService;

    @Override
    public boolean add(MerchandiseVipDiscount merchandiseVipDiscount) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(merchandiseVipDiscount.getMerchandiseItemId());
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + merchandiseVipDiscount.getMerchandiseItemId());
        merchandiseVipDiscount.setClassifyId(unifiedMerchandise.getMerchantClassifyId());
        merchandiseVipDiscount.setClassifyBSID(unifiedMerchandise.getMerchantClassifyBsid());
        long id = autoIdGenerator.get(ID_KEY);
        merchandiseVipDiscount.setId(id);
        return merchandiseVipDiscountDao.add(merchandiseVipDiscount);
    }

    @Override
    public MerchandiseVipDiscount get(Long id) {

        return merchandiseVipDiscountDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseVipDiscountDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseVipDiscount merchandiseVipDiscount) {

        return merchandiseVipDiscountDao.update(merchandiseVipDiscount);
    }

    @Override
    public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int count) {

        return merchandiseVipDiscountDao.page(query, start, count);
    }

    public List<MerchandiseVipDiscount> listAll() {

        return merchandiseVipDiscountDao.listAll();
    }

    @Override
    public Double statMin(Long merchandiseItemId) {

        return merchandiseVipDiscountDao.statMin(merchandiseItemId);
    }

    @Override
    public Double statMax(Long merchandiseItemId) {

        return merchandiseVipDiscountDao.statMax(merchandiseItemId);
    }

    @Override
    public MerchandiseVipDiscount get(Long userId, Long merchandiseItemId) {

        return merchandiseVipDiscountDao.get(userId, merchandiseItemId);
    }

    @Override
    public void save(MerchandiseVipDiscount merchandiseVipDiscount) {

        if (merchandiseVipDiscount.getDiscount() != 0) {
            merchandiseVipDiscount.setPaymentType(2);
        } else if (merchandiseVipDiscount.getPrice() != 0) {
            merchandiseVipDiscount.setPaymentType(1);
        }
        long userId = merchandiseVipDiscount.getUserId();
        long merchandiseItemId = merchandiseVipDiscount.getMerchandiseItemId();
        // 删除旧的大客户折扣
        MerchandiseVipDiscount oldDiscount = this.get(userId, merchandiseItemId);
        if (oldDiscount != null) {
            this.delete(oldDiscount.getId());
        }
        // 新增大客户折扣
        this.add(merchandiseVipDiscount);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        MerchandiseVipDiscountDate merchandiseVipDiscountDate = merchandiseVipDiscountDateService.getByUser(userId, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        if (merchandiseVipDiscountDate == null) {
            merchandiseVipDiscountDate = new MerchandiseVipDiscountDate();
            merchandiseVipDiscountDate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            merchandiseVipDiscountDate.setMonth(calendar.get(Calendar.MONTH) + 1);
            merchandiseVipDiscountDate.setYear(calendar.get(Calendar.YEAR));
            merchandiseVipDiscountDate.setUserId(userId);
            merchandiseVipDiscountDateService.add(merchandiseVipDiscountDate);
        }
        boolean addFlag = true;
        // 新增的和旧的大客户价格不相等
        if (oldDiscount != null) {
            if (merchandiseVipDiscount.getPrice() != 0 && merchandiseVipDiscount.getPrice() == oldDiscount.getPrice()) {
                addFlag = false;
            }
            if (merchandiseVipDiscount.getDiscount() != 0 && merchandiseVipDiscount.getDiscount() == oldDiscount.getDiscount()) {
                addFlag = false;
            }
        }
        if (addFlag) {
            // 新增大客户历史记录
            MerchandiseVipDiscountHistory history = new MerchandiseVipDiscountHistory();
            long id = autoIdGenerator.get(ID_KEY);
            history.setId(id);
            history.setUserId(userId);
            history.setMerchandiseItemId(merchandiseItemId);
            if (oldDiscount != null) {
                history.setPaymentTypeHistory(oldDiscount.getPaymentType());
                history.setDiscountHistory(oldDiscount.getDiscount());
                history.setPriceHistory(oldDiscount.getPrice());
            }
            history.setPaymentType(merchandiseVipDiscount.getPaymentType());
            history.setDiscount(merchandiseVipDiscount.getDiscount());
            history.setPrice(merchandiseVipDiscount.getPrice());
            history.setUpdateTime(new Date());
            merchandiseVipDiscountHistoryDao.add(history);
        }
    }

    @Override
    public List<MerchandiseVipDiscount> listByUser(long userId, long classifyId, int start, int count) {

        Classify classify = publicdataClient.getClassify(classifyId);
        String classifyBSID = "";
        if (classify != null) {
            classifyBSID = classify.getBsid();
        }
        return merchandiseVipDiscountDao.listByUser(userId, classifyId, classifyBSID, start, count);
    }

    @Override
    public boolean deleteByUser(long userId) {

        return merchandiseVipDiscountDao.deleteByUser(userId);
    }

    @Override
    public int countByUser(long userId, long classifyId) {

        Classify classify = publicdataClient.getClassify(classifyId);
        String classifyBSID = "";
        if (classify != null) {
            classifyBSID = classify.getBsid();
        }
        return merchandiseVipDiscountDao.countByUser(userId, classifyId, classifyBSID);
    }
}
