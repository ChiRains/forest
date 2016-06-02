package com.qcloud.component.brokerage.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;

public interface DistributionBrokerageService {
	
	public boolean add(DistributionBrokerage distributionBrokerage);	
	
	public DistributionBrokerage get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(DistributionBrokerage distributionBrokerage);

	public Page<DistributionBrokerage> page(DistributionBrokerageQuery query, int start, int count);
	
	public List<DistributionBrokerage> listAll();
}

