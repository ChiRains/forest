package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;
		
public interface AnalysisresultDao extends ISimpleDao<Analysisresult, Long> {

	public boolean add(Analysisresult analysisresult);	
	
	public Analysisresult get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Analysisresult analysisresult);
	
	public List<Analysisresult> list(List<Long> idList);
	
	public Map<Long, Analysisresult> map(Set<Long> idSet);
	
	public Page<Analysisresult> page(AnalysisresultQuery query, int start, int size);

	public List<Analysisresult> listAll();

	public Analysisresult getBMI(int type, double BMI);
	
}
