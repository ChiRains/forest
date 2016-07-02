package com.qcloud.component.goods.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.dao.MerchandiseEvaluationDao;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyToEvaluation;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum.StatusType;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MerchandiseEvaluationServiceImpl implements MerchandiseEvaluationService {

    @Autowired
    private MerchandiseEvaluationDao  merchandiseEvaluationDao;

    @Autowired
    private AutoIdGenerator           autoIdGenerator;

    private static final String       ID_KEY = "goods_merchandise_evaluation";

    @Autowired
    private SellercenterClient        sellercenterClient;

    @Autowired
    private MyClient                  myClient;

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private CommoditycenterClient     commoditycenterClient;

    @Override
    public boolean add(MerchandiseEvaluation merchandiseEvaluation) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseEvaluation.setId(id);
        return merchandiseEvaluationDao.add(merchandiseEvaluation);
    }

    // @Transactional
    // @Override
    // public boolean evaluate(MerchandiseEvaluation merchandiseEvaluation, Long merchantId, Long orderItemDetailId, Long orderId, Date orderDate) {
    //
    // merchandiseEvaluation.setAnonymous(merchandiseEvaluation.getAnonymous() != EnableType.ENABLE.getKey() ? EnableType.ENABLE.getKey() : merchandiseEvaluation.getAnonymous());
    // add(merchandiseEvaluation);
    // sellercenterClient.addMerchantEvaluation(merchandiseEvaluation.getId(), merchantId, merchandiseEvaluation.getMerchandiseId());
    // personalcenterClient.my().addMyEvaluation(merchandiseEvaluation.getId(), merchandiseEvaluation.getUserId(), merchandiseEvaluation.getMerchandiseId(), orderItemDetailId, orderDate);
    // personalcenterClient.my().updateMyOrderFormState(merchandiseEvaluation.getUserId(), orderId, MyOrderStateType.EVALUATED.getKey());
    // return true;
    // }
    @Override
    public boolean delete(Long id, Long merchandiseId) {

        return merchandiseEvaluationDao.delete(id, merchandiseId);
    }

    @Override
    public boolean update(MerchandiseEvaluation merchandiseEvaluation) {

        int star = merchandiseEvaluation.getStar();
        if (merchandiseEvaluation.getStatus() == StatusType.PASS.getKey()) {
            if (StarLevelType.CP.getKey() >= star) {
                unifiedMerchandiseService.increaseLowEvaluation(merchandiseEvaluation.getMerchandiseId());
            } else if (StarLevelType.ZP.getKey() >= star && star > StarLevelType.CP.getKey()) {
                unifiedMerchandiseService.increaseMiddleEvaluation(merchandiseEvaluation.getMerchandiseId());
            } else {
                unifiedMerchandiseService.increaseGoodEvaluation(merchandiseEvaluation.getMerchandiseId());
            }
        }
        return merchandiseEvaluationDao.update(merchandiseEvaluation);
    }

    @Override
    public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count) {

        return merchandiseEvaluationDao.page(query, start, count);
    }

    @Override
    public Page<MerchandiseEvaluation> page(long merchandiseId, StarLevelType starLevelType, int start, int count) {

        return merchandiseEvaluationDao.page(merchandiseId, starLevelType, start, count);
    }

    // @Override
    // public List<MerchandiseEvaluation> getListByMerchandiseIds(List<Long[]> idList) {
    //
    // List<MerchandiseEvaluation> list = new ArrayList<MerchandiseEvaluation>();
    // // 写for循环是为了后面会缓存处理
    // for (Long[] idArr : idList) {
    // MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationDao.get(idArr[0], idArr[1]);
    // list.add(merchandiseEvaluation);
    // }
    // return list;
    // }
    @Override
    public MerchandiseEvaluation get(long evaluationId, long merchandiseId) {

        return merchandiseEvaluationDao.get(evaluationId, merchandiseId);
    }

    // @Override
    // public EvaluationcentMerchandiseEntity getQEvaluationcentMerchandise(Long merchandiseId) {
    //
    // AssertUtil.assertNotNull(merchandiseId, "商品id不能为空!");
    // EvaluationcentMerchandiseEntity entity = new EvaluationcentMerchandiseEntity();
    // entity.setAllCount(merchandiseEvaluationDao.getEvaluationCount(merchandiseId, StarLevelType.ALL));
    // entity.setCpCount(merchandiseEvaluationDao.getEvaluationCount(merchandiseId, StarLevelType.CP));
    // entity.setZpCount(merchandiseEvaluationDao.getEvaluationCount(merchandiseId, StarLevelType.ZP));
    // entity.setHpCount(merchandiseEvaluationDao.getEvaluationCount(merchandiseId, StarLevelType.HP));
    // return entity;
    // }
    @Transactional
    @Override
    public boolean evaluate(long toEvaluationId, long userId, String content, int star) {

        QMyToEvaluation myToEvaluation = myClient.getMyToEvaluation(toEvaluationId);
        AssertUtil.assertNotNull(myToEvaluation, "待评价项不存在.");
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(myToEvaluation.getUnifiedMerchandiseId());
        MerchandiseEvaluation merchandiseEvaluation = new MerchandiseEvaluation();
        merchandiseEvaluation.setAnonymous(merchandiseEvaluation.getAnonymous() == EnableType.ENABLE.getKey() ? EnableType.ENABLE.getKey() : merchandiseEvaluation.getAnonymous());
        merchandiseEvaluation.setContent(content);
        merchandiseEvaluation.setMerchandiseId(myToEvaluation.getMerchandiseId());
        merchandiseEvaluation.setSpecifications(unifiedMerchandise.getSpecifications());
        merchandiseEvaluation.setStar(star);
        merchandiseEvaluation.setStatus(StatusType.UNDO.getKey());
        merchandiseEvaluation.setTime(new Date());
        merchandiseEvaluation.setUserId(userId);
        add(merchandiseEvaluation);
        sellercenterClient.addMerchantEvaluation(merchandiseEvaluation.getId(), myToEvaluation.getMerchantId(), merchandiseEvaluation.getMerchandiseId(), merchandiseEvaluation.getContent());
        myClient.addMyEvaluation(merchandiseEvaluation.getId(), merchandiseEvaluation.getUserId(), merchandiseEvaluation.getMerchandiseId(), toEvaluationId);
        return true;
    }
}
