package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.AnalysisresultDao;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.service.AnalysisresultService;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;
		
@Service
public class AnalysisresultServiceImpl implements AnalysisresultService{
	
	@Autowired
	private AnalysisresultDao analysisresultDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_analysisresult";

	@Override
	public boolean add(Analysisresult analysisresult) {
		long id = autoIdGenerator.get(ID_KEY);
		analysisresult.setId(id);
		
		return analysisresultDao.add(analysisresult);
	}

	@Override
	public Analysisresult get(Long id) {	
		return analysisresultDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return analysisresultDao.delete(id);
	}
	
	@Override
	public boolean update(Analysisresult analysisresult) {
		return analysisresultDao.update(analysisresult);
	}
	
	@Override
	public Page<Analysisresult> page(AnalysisresultQuery query, int start, int count) {
		return analysisresultDao.page(query, start, count);
	}
	
	public List<Analysisresult> listAll(){
		return analysisresultDao.listAll();
	}

	@Override
	public Analysisresult getBMI(int type, double BMI) {
		return analysisresultDao.getBMI(type,BMI);
	}
}

