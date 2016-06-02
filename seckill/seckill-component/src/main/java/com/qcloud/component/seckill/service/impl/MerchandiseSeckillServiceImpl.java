package com.qcloud.component.seckill.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.component.seckill.dao.MerchandiseSeckillDao;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.service.MerchandiseSeckillService;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;

@Service
public class MerchandiseSeckillServiceImpl implements MerchandiseSeckillService {

    @Autowired
    private MerchandiseSeckillDao merchandiseSeckillDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    private static final String   ID_KEY = "seckill_merchandise_seckill";

    @Override
    public boolean add(MerchandiseSeckill merchandiseSeckill) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseSeckill.setId(id);
        merchandiseSeckill.setEnable(1);
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseSeckill.getUnifiedMerchandiseId());
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + merchandiseSeckill.getUnifiedMerchandiseId());
        merchandiseSeckill.setOriginalStock(unifiedMerchandise.getStock());
        return merchandiseSeckillDao.add(merchandiseSeckill);
    }

    @Override
    public MerchandiseSeckill get(Long id) {

        return merchandiseSeckillDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseSeckillDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseSeckill merchandiseSeckill) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseSeckill.getUnifiedMerchandiseId());
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + merchandiseSeckill.getUnifiedMerchandiseId());
        merchandiseSeckill.setOriginalStock(unifiedMerchandise.getStock());
        return merchandiseSeckillDao.update(merchandiseSeckill);
    }

    @Override
    public Page<MerchandiseSeckill> page(MerchandiseSeckillQuery query, int start, int count) {

        return merchandiseSeckillDao.page(query, start, count);
    }

    public List<MerchandiseSeckill> listAll() {

        return merchandiseSeckillDao.listAll();
    }

    @Override
    public List<MerchandiseSeckill> listByScreenings(long screeningsId) {

        return merchandiseSeckillDao.listByScreenings(screeningsId);
    }

	@Override
	public List<MerchandiseSeckill> listByScreeningsAndQUnifiedMerchandiseId(
			long screeningsId, long qUnifiedMerchandiseId) {
		return merchandiseSeckillDao.listByScreeningsAndQUnifiedMerchandiseId(screeningsId, qUnifiedMerchandiseId);
	}
    
}
