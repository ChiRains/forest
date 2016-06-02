package com.qcloud.component.sellercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.SexpressDistrictDao;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.service.SexpressDistrictService;
import com.qcloud.component.sellercenter.model.query.SexpressDistrictQuery;
		
@Service
public class SexpressDistrictServiceImpl implements SexpressDistrictService{
	
	@Autowired
	private SexpressDistrictDao sexpressDistrictDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "sellercenter_sexpress_district";

	@Override
	public boolean add(SexpressDistrict sexpressDistrict) {
		long id = autoIdGenerator.get(ID_KEY);
		sexpressDistrict.setId(id);
		
		return sexpressDistrictDao.add(sexpressDistrict);
	}

	@Override
	public SexpressDistrict get(Long id) {	
		return sexpressDistrictDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return sexpressDistrictDao.delete(id);
	}
	
	@Override
	public boolean update(SexpressDistrict sexpressDistrict) {
		return sexpressDistrictDao.update(sexpressDistrict);
	}
	
	@Override
	public Page<SexpressDistrict> page(SexpressDistrictQuery query, int start, int count) {
		return sexpressDistrictDao.page(query, start, count);
	}
	
	public List<SexpressDistrict> listAll(){
		return sexpressDistrictDao.listAll();
	}

    @Override
    public List<SexpressDistrict> listByExpressId(Long id) {
        return sexpressDistrictDao.listByExpressId(id);
    }
}

