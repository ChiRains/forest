package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.AnalysisresultDao;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;
		
@Repository
public class AnalysisresultDaoMysqlImpl implements AnalysisresultDao {

	@Resource(name = "sqlOperator-forest")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(Analysisresult analysisresult) {
		return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.insert", analysisresult) == 1;
	}	
	
	@Override
	public Analysisresult get(Long id) {
		return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(Analysisresult analysisresult){
		return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.update", analysisresult) > 0;
	}
	
	@Override
	public List<Analysisresult> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Analysisresult> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Analysisresult> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Analysisresult> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.count4page",
				param);
		Page<Analysisresult> page = new Page<Analysisresult>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<Analysisresult> page(AnalysisresultQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);
        param.put("type", query.getType());
		List<Analysisresult> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.count4query",
				param);
		Page<Analysisresult> page = new Page<Analysisresult>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<Analysisresult> listAll(){	
		List<Analysisresult> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.listAll");
		return list;
	}

	@Override
	public Analysisresult getBMI(int type, double BMI) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", type);
		param.put("BMI", BMI);

		Analysisresult	result = sqlOperator.selectOne(
					"com.qcloud.project.forest.dao.mysql.mapper.AnalysisresultMapper.getBMI",
					param);	
		return result;
	}
}

