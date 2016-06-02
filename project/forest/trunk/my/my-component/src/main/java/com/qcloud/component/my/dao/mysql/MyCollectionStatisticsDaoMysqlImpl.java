package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionStatisticsDao;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.query.MyCollectionStatisticsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class MyCollectionStatisticsDaoMysqlImpl implements MyCollectionStatisticsDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyCollectionStatistics myCollectionStatistics) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.insert", myCollectionStatistics) == 1;
    }

    @Override
    public MyCollectionStatistics get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyCollectionStatistics myCollectionStatistics) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.update", myCollectionStatistics) > 0;
    }

    @Override
    public List<MyCollectionStatistics> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCollectionStatistics> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollectionStatistics> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyCollectionStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.count4page", param);
        Page<MyCollectionStatistics> page = new Page<MyCollectionStatistics>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyCollectionStatistics> page(MyCollectionStatisticsQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyCollectionStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.count4query", param);
        Page<MyCollectionStatistics> page = new Page<MyCollectionStatistics>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyCollectionStatistics> listAll() {

        List<MyCollectionStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.listAll");
        return list;
    }

    @Override
    public MyCollectionStatistics get(Long userId, long classifyId, int type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("classifyId", classifyId);
        param.put("type", type);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.getByUserAndClassifyAndType", param);
    }

    @Override
    public List<MyCollectionStatistics> listByUserAndType(long userId, int type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("type", type);
        List<MyCollectionStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionStatisticsMapper.listByUserAndType", param);
        return list;
    }
}
