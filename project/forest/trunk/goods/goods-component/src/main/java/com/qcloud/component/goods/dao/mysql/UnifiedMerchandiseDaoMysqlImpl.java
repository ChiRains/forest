package com.qcloud.component.goods.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.goods.dao.UnifiedMerchandiseDao;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class UnifiedMerchandiseDaoMysqlImpl implements UnifiedMerchandiseDao {

    @Resource(name = "sqlOperator-goods")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(UnifiedMerchandise unifiedMerchandise) {

        return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.insert", unifiedMerchandise) == 1;
    }

    @Override
    public UnifiedMerchandise get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.delete", id) > 0;
    }

    @Override
    public boolean update(UnifiedMerchandise unifiedMerchandise) {

        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.update", unifiedMerchandise) > 0;
    }

    @Override
    public List<UnifiedMerchandise> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UnifiedMerchandise> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UnifiedMerchandise> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UnifiedMerchandise> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.count4page", param);
        Page<UnifiedMerchandise> page = new Page<UnifiedMerchandise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("type", query.getType());
        param.put("merchantId", query.getMerchantId());
        List<UnifiedMerchandise> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.count4query", param);
        Page<UnifiedMerchandise> page = new Page<UnifiedMerchandise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<UnifiedMerchandise> listAll() {

        List<UnifiedMerchandise> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.listAll");
        return list;
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId) {

        List<UnifiedMerchandise> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.listByMerchandise", merchandiseId);
        return list;
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchandiseId", merchandiseId);
        param.put("type", type);
        List<UnifiedMerchandise> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.listByMerchandiseAndType", param);
        return list;
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type, int state) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchandiseId", merchandiseId);
        param.put("type", type);
        param.put("state", state);
        List<UnifiedMerchandise> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.listByMerchandiseAndTypeAndState", param);
        return list;
    }

    @Override
    public boolean increaseGoodEvaluation(long id) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("updateTime", new Date());
        param.put("id", id);
        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.increaseGoodEvaluation", param) > 0;
    }

    @Override
    public boolean increaseMiddleEvaluation(long id) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("updateTime", new Date());
        param.put("id", id);
        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.increaseMiddleEvaluation", param) > 0;
    }

    @Override
    public boolean increaseLowEvaluation(long id) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("updateTime", new Date());
        param.put("id", id);
        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.increaseLowEvaluation", param) > 0;
    }

    @Override
    public boolean updateSalesVolume(long id, int number) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("updateTime", new Date());
        param.put("id", id);
        param.put("number", number);
        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.updateSalesVolume", param) > 0;
    }

    @Override
    public boolean lockStock(long id, int stock) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("updateTime", new Date());
        param.put("id", id);
        param.put("stock", stock);
        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.lockStock", param) > 0;
    }

    @Override
    public Page<UnifiedMerchandise> select(UnifiedMerchandiseQuery query, int start, int size) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", size);
        param.put("merchantId", query.getMerchantId());
        param.put("name", query.getName());
        List<UnifiedMerchandise> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.list4Select", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.count4Select", param);
        Page<UnifiedMerchandise> page = new Page<UnifiedMerchandise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public UnifiedMerchandise getByCodeAndMerchant(String code, long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantId", merchantId);
        param.put("code", code);
        UnifiedMerchandise unifiedMerchandise = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.getByCodeAndMerchant", param);
        return unifiedMerchandise;
    }
}
