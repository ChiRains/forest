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
import com.qcloud.project.forest.dao.PartsMerchandiseDao;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;

@Repository
public class PartsMerchandiseDaoMysqlImpl implements PartsMerchandiseDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(PartsMerchandise partsMerchandise) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.insert", partsMerchandise) == 1;
    }

    @Override
    public PartsMerchandise get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.delete", id) > 0;
    }

    @Override
    public boolean update(PartsMerchandise partsMerchandise) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.update", partsMerchandise) > 0;
    }

    @Override
    public List<PartsMerchandise> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, PartsMerchandise> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<PartsMerchandise> listByClassifyId(Long classifyId) {

        return sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.listByClassifyId", classifyId);
    }

    @Override
    public Page<PartsMerchandise> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<PartsMerchandise> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.count4page", param);
        Page<PartsMerchandise> page = new Page<PartsMerchandise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<PartsMerchandise> page(PartsMerchandiseQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("classifyId", query.getClassifyId());
        param.put("state", query.getState());
        List<PartsMerchandise> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.count4query", param);
        Page<PartsMerchandise> page = new Page<PartsMerchandise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<PartsMerchandise> listAll() {

        List<PartsMerchandise> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.listAll");
        return list;
    }

    @Override
    public boolean deleteByClassify(long classifyId) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.deleteByClassify", classifyId) > 0;
    }

    @Override
    public boolean deleteByMerchandiseId(long merchandiseId) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.PartsMerchandiseMapper.deleteByMerchandiseId", merchandiseId) > 0;
    }
}
