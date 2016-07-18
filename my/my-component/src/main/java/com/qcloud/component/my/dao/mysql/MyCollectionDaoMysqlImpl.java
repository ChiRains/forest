package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionDao;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.model.query.MyCollectionQuery;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.TableSplitUtil;

@Repository
public class MyCollectionDaoMysqlImpl implements MyCollectionDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyCollection myCollection) {

        Map<String, Object> map = BeanUtils.transBean2Map(myCollection);
        map.put("table_name", getTableName(myCollection.getUserId()));
        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.insert", map) == 1;
    }

    @Override
    public MyCollection get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyCollection myCollection) {

        Map<String, Object> map = BeanUtils.transBean2Map(myCollection);
        map.put("table_name", getTableName(myCollection.getUserId()));
        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.update", map) > 0;
    }

    @Override
    public List<MyCollection> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCollection> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollection> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollection> page(MyCollectionQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollection> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollection> list(Long userId, CollectionType type, Long classifyId, int start, int count) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        if (classifyId == null || classifyId == 0) {
            classifyId = -1L;
        }
        map.put("classifyId", classifyId);
        map.put("start", start);
        map.put("count", count);
        map.put("type", type.getKey());
        List<MyCollection> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.list", map);
        return list;
    }

    @Override
    public MyCollection get(Long id, Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("id", id);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.get", map);
    }

    @Override
    public MyCollection getByObject(Long objId, Long userId, CollectionType type) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        map.put("objId", objId);
        map.put("type", type.getKey());
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.getByObject", map);
    }

    @Override
    public boolean delete(Long id, Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("id", id);
        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.delete", map) > 0;
    }

    private String getTableName(long userId) {

        return TableSplitUtil.getTableSplitName(userId, "my_my_collection", 100);
    }

    @Override
    public int countByUserId(Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.countByUserId", map);
    }

    @Override
    public List<MyCollection> listByUser(Long userId, CollectionType type, Long classifyId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        if (classifyId == null || classifyId == 0) {
            classifyId = -1L;
        }
        map.put("classifyId", classifyId);
        map.put("type", type.getKey());
        List<MyCollection> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCollectionMapper.listByUser", map);
        return list;
    }
}
