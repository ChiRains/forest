package com.qcloud.component.seckill.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;

public interface MerchandiseSeckillService {

    public boolean add(MerchandiseSeckill merchandiseSeckill);

    public MerchandiseSeckill get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseSeckill merchandiseSeckill);

    public Page<MerchandiseSeckill> page(MerchandiseSeckillQuery query, int start, int count);

    public List<MerchandiseSeckill> listAll();

    List<MerchandiseSeckill> listByScreenings(long screeningsId);
    
    /**
     * 同场次，获取商品应用的秒杀商品列表
     * @param screeningsId 场次
     * @param qUnifiedMerchandiseId 商品ID
     * @return
     */
    List<MerchandiseSeckill> listByScreeningsAndQUnifiedMerchandiseId(long screeningsId, long qUnifiedMerchandiseId);
}
