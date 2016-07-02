package com.qcloud.component.commoditycenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.commoditycenter.dao.MerchandiseBrowsingHistoryDao;
import com.qcloud.component.commoditycenter.model.MerchandiseBrowsingHistory;
import com.qcloud.component.commoditycenter.model.query.MerchandiseBrowsingHistoryQuery;

@Repository
public class MerchandiseBrowsingHistoryDaoMysqlImpl implements MerchandiseBrowsingHistoryDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {

        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.insert", merchandiseBrowsingHistory) == 1;
    }

    @Override
    public MerchandiseBrowsingHistory get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {

        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.update", merchandiseBrowsingHistory) > 0;
    }

    @Override
    public List<MerchandiseBrowsingHistory> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseBrowsingHistory> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseBrowsingHistory> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseBrowsingHistory> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.count4page", param);
        Page<MerchandiseBrowsingHistory> page = new Page<MerchandiseBrowsingHistory>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseBrowsingHistory> page(MerchandiseBrowsingHistoryQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseBrowsingHistory> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.count4query", param);
        Page<MerchandiseBrowsingHistory> page = new Page<MerchandiseBrowsingHistory>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseBrowsingHistory> listAll() {

        List<MerchandiseBrowsingHistory> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.listAll");
        return list;
    }

    @Override
    public List<MerchandiseBrowsingHistory> listByUser(long userId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        List<MerchandiseBrowsingHistory> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseBrowsingHistoryMapper.listByUser", param);
        return list;
    }
}
