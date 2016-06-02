package com.qcloud.component.brokerage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.DistributionBrokerageDao;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.service.DistributionBrokerageService;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;
		
@Service
public class DistributionBrokerageServiceImpl implements DistributionBrokerageService{
	
	@Autowired
	private DistributionBrokerageDao distributionBrokerageDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "brokerage_distribution_brokerage";

	@Override
	public boolean add(DistributionBrokerage distributionBrokerage) {
		long id = autoIdGenerator.get(ID_KEY);
		distributionBrokerage.setId(id);
		
		return distributionBrokerageDao.add(distributionBrokerage);
	}

	@Override
	public DistributionBrokerage get(Long id) {	
		return distributionBrokerageDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return distributionBrokerageDao.delete(id);
	}
	
	@Override
	public boolean update(DistributionBrokerage distributionBrokerage) {
		return distributionBrokerageDao.update(distributionBrokerage);
	}
	
	@Override
	public Page<DistributionBrokerage> page(DistributionBrokerageQuery query, int start, int count) {
		return distributionBrokerageDao.page(query, start, count);
	}
	
	public List<DistributionBrokerage> listAll(){
		return distributionBrokerageDao.listAll();
	}
}

