package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.query.SexpressDistrictQuery;
		
public interface SexpressDistrictDao extends ISimpleDao<SexpressDistrict, Long> {

	public boolean add(SexpressDistrict sexpressDistrict);	
	
	public SexpressDistrict get(Long id);

	public boolean delete(Long id);
	
	public boolean update(SexpressDistrict sexpressDistrict);
	
	public List<SexpressDistrict> list(List<Long> idList);
	
	public Map<Long, SexpressDistrict> map(Set<Long> idSet);
	
	public Page<SexpressDistrict> page(SexpressDistrictQuery query, int start, int size);

	public List<SexpressDistrict> listAll();
	
	public List<SexpressDistrict > listByExpressId(Long id);
}
