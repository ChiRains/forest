package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;

public interface AnalysisresultService {
	
	public boolean add(Analysisresult analysisresult);	
	
	public Analysisresult get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Analysisresult analysisresult);

	public Page<Analysisresult> page(AnalysisresultQuery query, int start, int count);
	
	public List<Analysisresult> listAll();


	public Analysisresult getBMI(int type, double BMI);

}

