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
import com.qcloud.project.forest.dao.MedicationRemindersDao;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;

@Repository
public class MedicationRemindersDaoMysqlImpl implements MedicationRemindersDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MedicationReminders medicationReminders) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.insert", medicationReminders) == 1;
    }

    @Override
    public MedicationReminders get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MedicationReminders medicationReminders) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.update", medicationReminders) > 0;
    }

    @Override
    public List<MedicationReminders> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MedicationReminders> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MedicationReminders> listByNormal(Long themeId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationReminders> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MedicationReminders> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.count4page", param);
        Page<MedicationReminders> page = new Page<MedicationReminders>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MedicationReminders> page(MedicationRemindersQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MedicationReminders> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.count4query", param);
        Page<MedicationReminders> page = new Page<MedicationReminders>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MedicationReminders> listAll() {

        List<MedicationReminders> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.listAll");
        return list;
    }

    @Override
    public List<MedicationReminders> listByThemeId(Long themeId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("themeId", themeId);
        List<MedicationReminders> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersMapper.listByThemeId", param);
        return list;
    }
}
