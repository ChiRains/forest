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
import com.qcloud.component.goods.dao.MerchandiseSpecificationsRelationDao;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsRelationQuery;
		
@Repository
public class MerchandiseSpecificationsRelationDaoMysqlImpl implements MerchandiseSpecificationsRelationDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.insert", merchandiseSpecificationsRelation) == 1;
	}	
	
	@Override
	public MerchandiseSpecificationsRelation get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.update", merchandiseSpecificationsRelation) > 0;
	}
	
	@Override
	public List<MerchandiseSpecificationsRelation> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseSpecificationsRelation> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseSpecificationsRelation> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseSpecificationsRelation> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.count4page",
				param);
		Page<MerchandiseSpecificationsRelation> page = new Page<MerchandiseSpecificationsRelation>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchandiseSpecificationsRelation> page(MerchandiseSpecificationsRelationQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseSpecificationsRelation> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.count4query",
				param);
		Page<MerchandiseSpecificationsRelation> page = new Page<MerchandiseSpecificationsRelation>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchandiseSpecificationsRelation> listAll(){	
		List<MerchandiseSpecificationsRelation> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.listAll");
		return list;
	}

    @Override
    public boolean updateSpecByMap(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {

        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.updateSpecByMap",merchandiseSpecificationsRelation)>0;
    }

    @Override
    public List<MerchandiseSpecificationsRelation> listByMerchandiseId(Long merchandiseId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchandiseId", merchandiseId);
        List<MerchandiseSpecificationsRelation> list = sqlOperator.selectList(
                "com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.listByMerchandiseId",param);
        return list;
    }

    @Override
    public boolean deleteByMerchandiseId(Long merchandiseIds) {

        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.deleteByMerchandiseId",merchandiseIds)>0;
    }

    @Override
    public List<MerchandiseSpecificationsRelation> listByMap(Long merchandiseId, Long attributeId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchandiseId", merchandiseId);
        param.put("attributeId", attributeId);
        List<MerchandiseSpecificationsRelation> list = sqlOperator.selectList(
                "com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsRelationMapper.listByMap",param);
        return list;
    }
}

