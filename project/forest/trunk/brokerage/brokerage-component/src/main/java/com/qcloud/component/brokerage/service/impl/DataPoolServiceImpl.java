package com.qcloud.component.brokerage.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.brokerage.dao.DataPoolDao;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.FormulaSqlResult;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;
import com.qcloud.component.brokerage.service.DataPoolService;
import com.qcloud.pirates.data.Page;
		
@Service
public class DataPoolServiceImpl implements DataPoolService{
	
	@Autowired
	private DataPoolDao dataPoolDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "brokerage_data_pool";

	@Override
	public boolean add(DataPool dataPool) {
		long id = autoIdGenerator.get(ID_KEY);
		dataPool.setId(id);
		
		dataPool.setGenerateTime(new Date());
		return dataPoolDao.add(dataPool);
	}

	@Override
	public DataPool get(Long id) {	
		return dataPoolDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return dataPoolDao.delete(id);
	}
	
	@Override
	public boolean update(DataPool dataPool) {
		return dataPoolDao.update(dataPool);
	}
	
	@Override
	public Page<DataPool> page(DataPoolQuery query, int start, int count) {
		return dataPoolDao.page(query, start, count);
	}
	
	public List<DataPool> listAll(){
		return dataPoolDao.listAll();
	}

    @Override
    public List<FormulaSqlResult> query(String sql) {
        return dataPoolDao.query(sql);
    }
}

