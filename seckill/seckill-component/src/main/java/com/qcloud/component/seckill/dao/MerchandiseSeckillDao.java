package com.qcloud.component.seckill.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;
		
public interface MerchandiseSeckillDao extends ISimpleDao<MerchandiseSeckill, Long> {

	public boolean add(MerchandiseSeckill merchandiseSeckill);	
	
	public MerchandiseSeckill get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseSeckill merchandiseSeckill);
	
	public List<MerchandiseSeckill> list(List<Long> idList);
	
	public Map<Long, MerchandiseSeckill> map(Set<Long> idSet);
	
	public Page<MerchandiseSeckill> page(MerchandiseSeckillQuery query, int start, int size);

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
