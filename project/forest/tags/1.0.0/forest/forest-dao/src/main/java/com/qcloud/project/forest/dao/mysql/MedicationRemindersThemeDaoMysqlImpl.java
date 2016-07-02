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
import com.qcloud.project.forest.dao.MedicationRemindersThemeDao;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.model.query.MedicationRemindersThemeQuery;

@Repository
public class MedicationRemindersThemeDaoMysqlImpl implements MedicationRemindersThemeDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MedicationRemindersTheme medicationRemindersTheme) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.insert", medicationRemindersTheme) == 1;
    }

    @Override
    public MedicationRemindersTheme get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MedicationRemindersTheme medicationRemindersTheme) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.update", medicationRemindersTheme) > 0;
    }

    @Override
    public List<MedicationRemindersTheme> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MedicationRemindersTheme> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MedicationRemindersTheme> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationRemindersTheme> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MedicationRemindersTheme> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.count4page", param);
        Page<MedicationRemindersTheme> page = new Page<MedicationRemindersTheme>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MedicationRemindersTheme> page(MedicationRemindersThemeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", query.getUserId());
        List<MedicationRemindersTheme> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.count4query", param);
        Page<MedicationRemindersTheme> page = new Page<MedicationRemindersTheme>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MedicationRemindersTheme> listAll() {

        List<MedicationRemindersTheme> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationRemindersThemeMapper.listAll");
        return list;
    }
}
