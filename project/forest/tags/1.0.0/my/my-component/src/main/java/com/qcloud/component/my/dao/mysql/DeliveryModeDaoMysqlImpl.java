package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.DeliveryModeDao;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.query.DeliveryModeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class DeliveryModeDaoMysqlImpl implements DeliveryModeDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(DeliveryMode deliveryMode) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.insert", deliveryMode) == 1;
    }

    @Override
    public DeliveryMode get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(DeliveryMode deliveryMode) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.update", deliveryMode) > 0;
    }

    @Override
    public List<DeliveryMode> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, DeliveryMode> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<DeliveryMode> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DeliveryMode> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.count4page", param);
        Page<DeliveryMode> page = new Page<DeliveryMode>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<DeliveryMode> page(DeliveryModeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DeliveryMode> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.count4query", param);
        Page<DeliveryMode> page = new Page<DeliveryMode>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<DeliveryMode> listAll() {

        List<DeliveryMode> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.listAll");
        return list;
    }

    @Override
    public DeliveryMode getByUser(Long userId) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.DeliveryModeMapper.getByUser", userId);
    }
}
