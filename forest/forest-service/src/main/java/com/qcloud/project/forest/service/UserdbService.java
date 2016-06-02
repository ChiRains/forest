package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.model.query.UserdbQuery;

public interface UserdbService {
	
	public boolean add(Userdb userdb);	
	
	public Userdb get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Userdb userdb);

	public Page<Userdb> page(UserdbQuery query, int start, int count);
	
	public List<Userdb> listAll();
}

