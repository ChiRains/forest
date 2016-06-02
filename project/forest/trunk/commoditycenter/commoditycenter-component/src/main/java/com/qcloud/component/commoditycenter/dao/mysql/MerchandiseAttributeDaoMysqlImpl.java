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
import com.qcloud.component.commoditycenter.dao.MerchandiseAttributeDao;
import com.qcloud.component.commoditycenter.model.MerchandiseAttribute;
import com.qcloud.component.commoditycenter.model.query.MerchandiseAttributeQuery;
@Repository
public class MerchandiseAttributeDaoMysqlImpl implements MerchandiseAttributeDao {
    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseAttribute merchandiseAttribute) {
        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.insert", merchandiseAttribute) == 1;
    }

    @Override
    public MerchandiseAttribute get(Long id) {
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {
        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseAttribute merchandiseAttribute) {
        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.update", merchandiseAttribute) > 0;
    }

    @Override
    public List<MerchandiseAttribute> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseAttribute> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseAttribute> page(int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseAttribute> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.count4page", param);
        Page<MerchandiseAttribute> page = new Page<MerchandiseAttribute>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseAttribute> page(MerchandiseAttributeQuery query, int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseAttribute> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.count4query", param);
        Page<MerchandiseAttribute> page = new Page<MerchandiseAttribute>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseAttribute> listAll() {
        List<MerchandiseAttribute> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.listAll");
        return list;
    }

    @Override
    public List<MerchandiseAttribute> listByMerchandise(long merchandiseId) {
        List<MerchandiseAttribute> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseAttributeMapper.listByMerchandise", merchandiseId);
        return list;
    }
}
