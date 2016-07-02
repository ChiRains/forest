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
import com.qcloud.component.commoditycenter.dao.CombinationMerchandiseItemDao;
import com.qcloud.component.commoditycenter.model.CombinationMerchandiseItem;
import com.qcloud.component.commoditycenter.model.query.CombinationMerchandiseItemQuery;

@Repository
public class CombinationMerchandiseItemDaoMysqlImpl implements CombinationMerchandiseItemDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(CombinationMerchandiseItem combinationMerchandiseItem) {

        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.insert", combinationMerchandiseItem) == 1;
    }

    @Override
    public CombinationMerchandiseItem get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.delete", id) > 0;
    }

    @Override
    public boolean update(CombinationMerchandiseItem combinationMerchandiseItem) {

        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.update", combinationMerchandiseItem) > 0;
    }

    @Override
    public List<CombinationMerchandiseItem> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, CombinationMerchandiseItem> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CombinationMerchandiseItem> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<CombinationMerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.count4page", param);
        Page<CombinationMerchandiseItem> page = new Page<CombinationMerchandiseItem>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<CombinationMerchandiseItem> page(CombinationMerchandiseItemQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<CombinationMerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.count4query", param);
        Page<CombinationMerchandiseItem> page = new Page<CombinationMerchandiseItem>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<CombinationMerchandiseItem> listAll() {

        List<CombinationMerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.listAll");
        return list;
    }

    @Override
    public List<CombinationMerchandiseItem> listByCombinationMerchandise(long combinationMerchandiseId) {

        List<CombinationMerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.listByCombinationMerchandise", combinationMerchandiseId);
        return list;
    }

    @Override
    public List<CombinationMerchandiseItem> list(Map where) {

        return sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.list", where);
    }

    @Override
    public boolean delete(Map where) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.del", where) > 0;
    }

    @Override
    public List<CombinationMerchandiseItem> listByMerchandiseItem(Long merchandiseItemId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchandiseItemId", merchandiseItemId);
        List<CombinationMerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.listByMerchandiseItem", param);
        return list;
    }

    @Override
    public int countByMerchandiseItem(Long merchandiseItemId) {
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.CombinationMerchandiseItemMapper.countByMerchandiseItem", merchandiseItemId);
    }
}
