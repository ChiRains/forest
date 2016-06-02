package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.query.UpgradeOrderQuery;
		
public interface UpgradeOrderDao extends ISimpleDao<UpgradeOrder, Long> {

	public boolean add(UpgradeOrder upgradeOrder);	
	
	public UpgradeOrder get(Long id);

	public boolean delete(Long id);
	
	public boolean update(UpgradeOrder upgradeOrder);
	
	public List<UpgradeOrder> list(List<Long> idList);
	
	public Map<Long, UpgradeOrder> map(Set<Long> idSet);
	
	public Page<UpgradeOrder> page(UpgradeOrderQuery query, int start, int size);

	public List<UpgradeOrder> listAll();
	
}
