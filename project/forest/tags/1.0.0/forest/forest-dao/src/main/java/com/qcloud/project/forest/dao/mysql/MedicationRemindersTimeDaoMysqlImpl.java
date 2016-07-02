package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.MedicationRemindersTimeDao;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.query.MedicationRemindersTimeQuery;

@Repository
public class MedicationRemindersTimeDaoMysqlImpl implements MedicationRemindersTimeDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MedicationRemindersTime medicationRemindersTime) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.insert", medicationRemindersTime) == 1;
    }

    @Override
    public MedicationRemindersTime get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MedicationRemindersTime medicationRemindersTime) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.update", medicationRemindersTime) > 0;
    }

    @Override
    public List<MedicationRemindersTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MedicationRemindersTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MedicationRemindersTime> listByExcuteTime(String excuteTime) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("excuteTime", excuteTime);
        return sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.listByExcuteTime", param);
    }

    @Override
    public Page<MedicationRemindersTime> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MedicationRemindersTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.count4page", param);
        Page<MedicationRemindersTime> page = new Page<MedicationRemindersTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MedicationRemindersTime> page(MedicationRemindersTimeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MedicationRemindersTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.count4query", param);
        Page<MedicationRemindersTime> page = new Page<MedicationRemindersTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MedicationRemindersTime> listAll() {

        List<MedicationRemindersTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.listAll");
        return list;
    }

    @Override
    public Boolean deleteByReminderId(Long medicationRemindersId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("reminderId", medicationRemindersId);
        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersTimeMapper.deleteByReminderId", param) > 0;
    }
}
