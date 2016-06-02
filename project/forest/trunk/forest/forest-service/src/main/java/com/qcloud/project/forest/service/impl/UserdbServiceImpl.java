package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.UserdbDao;
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.service.UserdbService;
import com.qcloud.project.forest.model.query.UserdbQuery;
		
@Service
public class UserdbServiceImpl implements UserdbService{
	
	@Autowired
	private UserdbDao userdbDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_userdb";

	@Override
	public boolean add(Userdb userdb) {
		long id = autoIdGenerator.get(ID_KEY);
		userdb.setId(id);
		
		return userdbDao.add(userdb);
	}

	@Override
	public Userdb get(Long id) {	
		return userdbDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return userdbDao.delete(id);
	}
	
	@Override
	public boolean update(Userdb userdb) {
		return userdbDao.update(userdb);
	}
	
	@Override
	public Page<Userdb> page(UserdbQuery query, int start, int count) {
		return userdbDao.page(query, start, count);
	}
	
	public List<Userdb> listAll(){
		return userdbDao.listAll();
	}
}

