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
import com.qcloud.project.forest.dao.ConfigDao;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.model.query.ConfigQuery;

@Repository
public class ConfigDaoMysqlImpl implements ConfigDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Config config) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.insert", config) == 1;
    }

    @Override
    public Config get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Config config) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.update", config) > 0;
    }

    @Override
    public List<Config> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Config> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<Config> listByType(Integer type) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Config> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Config> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.count4page", param);
        Page<Config> page = new Page<Config>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Config> page(ConfigQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Config> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.count4query", param);
        Page<Config> page = new Page<Config>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Config> listAll() {

        List<Config> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.listAll");
        return list;
    }

    @Override
    public Config getByCode(String code) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", 0);
        param.put("count", 1);
        param.put("code", code);
        Config config = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ConfigMapper.list4query", param);
        return config;
    }
}
