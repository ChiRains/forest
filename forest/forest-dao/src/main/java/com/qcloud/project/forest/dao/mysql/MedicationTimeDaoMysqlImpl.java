package com.qcloud.project.forest.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.dao.MedicationTimeDao;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.model.query.MedicationTimeQuery;

@Repository
public class MedicationTimeDaoMysqlImpl implements MedicationTimeDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MedicationTime medicationTime) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.insert", medicationTime) == 1;
    }

    @Override
    public MedicationTime get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MedicationTime medicationTime) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.update", medicationTime) > 0;
    }

    @Override
    public List<MedicationTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MedicationTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MedicationTime> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    public List<MedicationTime> listByExcuteTime(Date excuteTime) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("beginExcuteTime", DateUtil.date2String(DateUtil.addTime(excuteTime, -1), DateUtil.FORMAT_STRING));
        param.put("endExcuteTime", DateUtil.date2String(excuteTime, DateUtil.FORMAT_STRING));
        List<MedicationTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.listByExcuteTime", param);
        return list;
    }

    @Override
    public Page<MedicationTime> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MedicationTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.count4page", param);
        Page<MedicationTime> page = new Page<MedicationTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MedicationTime> page(MedicationTimeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MedicationTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.count4query", param);
        Page<MedicationTime> page = new Page<MedicationTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MedicationTime> listAll() {

        List<MedicationTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.listAll");
        return list;
    }

    @Override
    public List<MedicationTime> listByMedicationId(Long medicationId) {

        List<MedicationTime> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.MedicationTimeMapper.listByMedicationId", medicationId);
        return list;
    }
}
