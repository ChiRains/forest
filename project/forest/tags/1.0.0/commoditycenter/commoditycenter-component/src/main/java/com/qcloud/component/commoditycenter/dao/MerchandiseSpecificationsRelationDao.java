package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.commoditycenter.model.query.MerchandiseSpecificationsRelationQuery;
		
public interface MerchandiseSpecificationsRelationDao extends ISimpleDao<MerchandiseSpecificationsRelation, Long> {

	public boolean add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);	
	
	public MerchandiseSpecificationsRelation get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);
	
	public List<MerchandiseSpecificationsRelation> list(List<Long> idList);
	
	public Map<Long, MerchandiseSpecificationsRelation> map(Set<Long> idSet);
	
	public Page<MerchandiseSpecificationsRelation> page(MerchandiseSpecificationsRelationQuery query, int start, int size);

	public List<MerchandiseSpecificationsRelation> listAll();
	
	public boolean updateSpecByMap(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);
	
	public List<MerchandiseSpecificationsRelation>  listByMerchandiseId(Long merchandiseId);
	
	public boolean deleteByMerchandiseId(Long merchandiseIds);
	
	public List<MerchandiseSpecificationsRelation> listByMap(Long merchandiseId,Long attributeId);
	
}
