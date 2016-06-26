package com.qcloud.component.goods.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.goods.dao.MerchandiseEvaluationDao;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;
		
@Repository
public class MerchandiseEvaluationDaoMysqlImpl implements MerchandiseEvaluationDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchandiseEvaluation merchandiseEvaluation) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.insert", merchandiseEvaluation) == 1;
	}	
	
	@Override
	public MerchandiseEvaluation get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchandiseEvaluation merchandiseEvaluation){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.update", merchandiseEvaluation) > 0;
	}
	
	@Override
	public List<MerchandiseEvaluation> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseEvaluation> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
															
			public List<MerchandiseEvaluation> listByMerchandiseId(Long merchandiseId){
				throw new NotImplementedException();
			}
				
															
			public List<MerchandiseEvaluation> listByStar(Integer star){
				throw new NotImplementedException();
			}
				
															
			public List<MerchandiseEvaluation> listByTime(Date time){
				throw new NotImplementedException();
			}
				
															
			public List<MerchandiseEvaluation> listByStatus(Integer status){
				throw new NotImplementedException();
			}
				
	@Override
	public Page<MerchandiseEvaluation> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseEvaluation> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.count4page",
				param);
		Page<MerchandiseEvaluation> page = new Page<MerchandiseEvaluation>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseEvaluation> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.count4query",
				param);
		Page<MerchandiseEvaluation> page = new Page<MerchandiseEvaluation>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchandiseEvaluation> listAll(){	
		List<MerchandiseEvaluation> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseEvaluationMapper.listAll");
		return list;
	}
}

