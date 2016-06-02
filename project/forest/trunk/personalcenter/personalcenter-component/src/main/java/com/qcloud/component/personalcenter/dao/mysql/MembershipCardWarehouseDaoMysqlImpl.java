package com.qcloud.component.personalcenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.personalcenter.dao.MembershipCardWarehouseDao;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;

@Repository
public class MembershipCardWarehouseDaoMysqlImpl implements MembershipCardWarehouseDao {

    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MembershipCardWarehouse membershipCardWarehouse) {

        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.insert", membershipCardWarehouse) == 1;
    }

    @Override
    public MembershipCardWarehouse get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MembershipCardWarehouse membershipCardWarehouse) {

        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.update", membershipCardWarehouse) > 0;
    }

    @Override
    public List<MembershipCardWarehouse> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MembershipCardWarehouse> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MembershipCardWarehouse> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MembershipCardWarehouse> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.count4page", param);
        Page<MembershipCardWarehouse> page = new Page<MembershipCardWarehouse>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MembershipCardWarehouse> page(MembershipCardWarehouseQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("cardNumber", query.getCardNumber());
        List<MembershipCardWarehouse> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.count4query", param);
        Page<MembershipCardWarehouse> page = new Page<MembershipCardWarehouse>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MembershipCardWarehouse> listAll() {

        List<MembershipCardWarehouse> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.listAll");
        return list;
    }

    @Override
    public MembershipCardWarehouse getByCardNumber(String cardNumber) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MembershipCardWarehouseMapper.getByCardNumber", cardNumber);
    }
}
