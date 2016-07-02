package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionObjStatisticsDao;
import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.query.MyCollectionObjStatisticsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class MyCollectionObjStatisticsDaoMysqlImpl implements MyCollectionObjStatisticsDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyCollectionObjStatistics myCollectionObjStatistics) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.insert", myCollectionObjStatistics) == 1;
    }

    @Override
    public MyCollectionObjStatistics get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyCollectionObjStatistics myCollectionObjStatistics) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.update", myCollectionObjStatistics) > 0;
    }

    @Override
    public List<MyCollectionObjStatistics> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCollectionObjStatistics> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollectionObjStatistics> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyCollectionObjStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.count4page", param);
        Page<MyCollectionObjStatistics> page = new Page<MyCollectionObjStatistics>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyCollectionObjStatistics> page(MyCollectionObjStatisticsQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyCollectionObjStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.count4query", param);
        Page<MyCollectionObjStatistics> page = new Page<MyCollectionObjStatistics>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyCollectionObjStatistics> listAll() {

        List<MyCollectionObjStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.listAll");
        return list;
    }

    @Override
    public MyCollectionObjStatistics get(long objId, int type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("objId", objId);
        param.put("type", type);
        List<MyCollectionObjStatistics> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionObjStatisticsMapper.getByObjAndType", param);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
