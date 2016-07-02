package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.AnalysisresultDao;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;

@Repository
public class AnalysisresultDaoCacheImpl implements AnalysisresultDao {
	
	@Autowired
	private AnalysisresultDao analysisresultDaoMysqlImpl;
	
	@Autowired
	private AnalysisresultDao analysisresultDaoRedisImpl;

	@Override
	public boolean add(Analysisresult analysisresult) {
		return analysisresultDaoMysqlImpl.add(analysisresult);
	}

	@Override
	public Analysisresult get(Long id) {
		//return CacheLoader.get(analysisresultDaoRedisImpl, analysisresultDaoMysqlImpl, id);
		return analysisresultDaoMysqlImpl.get(id);

	}

	@Override
	public boolean delete(Long id){
		return analysisresultDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Analysisresult analysisresult){
		return analysisresultDaoMysqlImpl.update(analysisresult);
	}
	
	@Override
	public List<Analysisresult> list(List<Long> idList) {
		return CacheLoader.list(analysisresultDaoRedisImpl, analysisresultDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Analysisresult> map(Set<Long> idSet){
		return CacheLoader.map(analysisresultDaoRedisImpl, analysisresultDaoMysqlImpl, idSet);
	}

	@Override
	public Page<Analysisresult> page(int start, int count){
		return analysisresultDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Analysisresult> page(AnalysisresultQuery query, int start, int count){
		return analysisresultDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Analysisresult> listAll(){
		return analysisresultDaoMysqlImpl.listAll();
	}

	@Override
	public Analysisresult getBMI(int type, double BMI) {
		return analysisresultDaoMysqlImpl.getBMI(type, BMI);
	}
}

