package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.commoditycenter.model.MerchandiseDealRecord;
import com.qcloud.component.commoditycenter.model.query.MerchandiseDealRecordQuery;
		
public interface MerchandiseDealRecordDao extends ISimpleDao<MerchandiseDealRecord, Long> {

	public boolean add(MerchandiseDealRecord merchandiseDealRecord);	
	
	public MerchandiseDealRecord get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseDealRecord merchandiseDealRecord);
	
	public List<MerchandiseDealRecord> list(List<Long> idList);
	
	public Map<Long, MerchandiseDealRecord> map(Set<Long> idSet);
	
	public Page<MerchandiseDealRecord> page(MerchandiseDealRecordQuery query, int start, int size);

	public List<MerchandiseDealRecord> listAll();
	
	List<MerchandiseDealRecord> listByMerchandise(Long merchandiseId, int start, int count);	
}
