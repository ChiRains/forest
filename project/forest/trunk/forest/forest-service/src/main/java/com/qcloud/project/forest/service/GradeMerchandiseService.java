package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;

public interface GradeMerchandiseService {
	
	public boolean add(GradeMerchandise gradeMerchandise);	
	
	public GradeMerchandise get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(GradeMerchandise gradeMerchandise);

	public Page<GradeMerchandise> page(GradeMerchandiseQuery query, int start, int count);
	
	public List<GradeMerchandise> listAll();
}

