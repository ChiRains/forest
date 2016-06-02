package com.qcloud.component.commoditycenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.TableSplitUtil;
import com.qcloud.component.commoditycenter.dao.MerchandiseDealRecordDao;
import com.qcloud.component.commoditycenter.model.MerchandiseDealRecord;
import com.qcloud.component.commoditycenter.model.query.MerchandiseDealRecordQuery;

@Repository
public class MerchandiseDealRecordDaoMysqlImpl implements MerchandiseDealRecordDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseDealRecord merchandiseDealRecord) {

        Map<String, Object> map = BeanUtils.transBean2Map(merchandiseDealRecord);
        map.put("table_name", getTableName(merchandiseDealRecord.getMerchandiseId()));
        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.insert", map) == 1;
    }

    @Override
    public MerchandiseDealRecord get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseDealRecord merchandiseDealRecord) {

        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.update", merchandiseDealRecord) > 0;
    }

    @Override
    public List<MerchandiseDealRecord> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseDealRecord> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseDealRecord> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseDealRecord> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.count4page", param);
        Page<MerchandiseDealRecord> page = new Page<MerchandiseDealRecord>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseDealRecord> page(MerchandiseDealRecordQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseDealRecord> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.count4query", param);
        Page<MerchandiseDealRecord> page = new Page<MerchandiseDealRecord>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseDealRecord> listAll() {

        List<MerchandiseDealRecord> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.listAll");
        return list;
    }

    @Override
    public List<MerchandiseDealRecord> listByMerchandise(Long merchandiseId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchandiseId", merchandiseId);
        param.put("table_name", getTableName(merchandiseId));
        List<MerchandiseDealRecord> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseDealRecordMapper.listByMerchandise", param);
        return list;
    }

    private String getTableName(long userId) {

        return TableSplitUtil.getTableSplitName(userId, "commoditycenter_merchandise_deal_record", 100);
    }
}
