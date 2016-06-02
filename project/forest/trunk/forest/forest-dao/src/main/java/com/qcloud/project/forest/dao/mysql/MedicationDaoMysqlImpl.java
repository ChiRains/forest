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
import com.qcloud.project.forest.dao.MedicationDao;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.query.MedicationQuery;

@Repository
public class MedicationDaoMysqlImpl implements MedicationDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Medication medication) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.insert", medication) == 1;
    }

    @Override
    public Medication get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Medication medication) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.update", medication) > 0;
    }

    @Override
    public List<Medication> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Medication> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Medication> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Medication> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.count4page", param);
        Page<Medication> page = new Page<Medication>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Medication> page(MedicationQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", query.getUserId());
        List<Medication> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.count4query", param);
        Page<Medication> page = new Page<Medication>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Medication> listAll() {

        List<Medication> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationMapper.listAll");
        return list;
    }
}
