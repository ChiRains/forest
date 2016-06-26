package com.qcloud.component.goods.dao.mysql;

import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MerchandiseSpecificationsDaoMysqlImpl implements MerchandiseSpecificationsDao {

    @Resource(name = "sqlOperator-goods")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseSpecifications merchandiseSpecifications) {

        return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.insert", merchandiseSpecifications) == 1;
    }

    @Override
    public MerchandiseSpecifications get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseSpecifications merchandiseSpecifications) {

        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.update", merchandiseSpecifications) > 0;
    }

    @Override
    public List<MerchandiseSpecifications> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseSpecifications> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseSpecifications> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.count4page", param);
        Page<MerchandiseSpecifications> page = new Page<MerchandiseSpecifications>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseSpecifications> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.count4query", param);
        Page<MerchandiseSpecifications> page = new Page<MerchandiseSpecifications>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseSpecifications> listAll() {

        List<MerchandiseSpecifications> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.listAll");
        return list;
    }

    @Override
    public List<MerchandiseSpecifications> listByMerchandise(Long merchandiseId) {

        List<MerchandiseSpecifications> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.listByMerchandise", merchandiseId);
        return list;
    }

    @Override
    public MerchandiseSpecifications get(HashMap where) {

        where.put("start", 0);
        where.put("count", 1);
        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.list", where);
    }

    @Override
    public List<MerchandiseSpecifications> list(HashMap where) {

        return sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.list", where);
    }

    @Override
    public Page<MerchandiseSpecifications> page(HashMap where, int start, int size) {

        where.put("start", start);
        where.put("count", size);
        List<MerchandiseSpecifications> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.list", where);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.count", where);
        Page<MerchandiseSpecifications> merchandiseSpecificationsPage = new Page<MerchandiseSpecifications>();
        merchandiseSpecificationsPage.setCount(total);
        merchandiseSpecificationsPage.setData(list);
        return merchandiseSpecificationsPage;
    }
}
