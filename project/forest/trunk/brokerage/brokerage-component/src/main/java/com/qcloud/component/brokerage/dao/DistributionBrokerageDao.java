package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;
		
public interface DistributionBrokerageDao extends ISimpleDao<DistributionBrokerage, Long> {

	public boolean add(DistributionBrokerage distributionBrokerage);	
	
	public DistributionBrokerage get(Long id);

	public boolean delete(Long id);
	
	public boolean update(DistributionBrokerage distributionBrokerage);
	
	public List<DistributionBrokerage> list(List<Long> idList);
	
	public Map<Long, DistributionBrokerage> map(Set<Long> idSet);
	
	public Page<DistributionBrokerage> page(DistributionBrokerageQuery query, int start, int size);

	public List<DistributionBrokerage> listAll();
	
}
