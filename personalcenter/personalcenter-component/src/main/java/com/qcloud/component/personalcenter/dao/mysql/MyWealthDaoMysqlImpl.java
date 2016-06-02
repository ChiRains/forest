package com.qcloud.component.personalcenter.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.personalcenter.dao.MyWealthDao;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;

@Repository
public class MyWealthDaoMysqlImpl implements MyWealthDao {

    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyWealth myWealth) {

        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.insert", myWealth) == 1;
    }

    @Override
    public MyWealth get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyWealth myWealth) {

        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.update", myWealth) > 0;
    }

    @Override
    public List<MyWealth> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyWealth> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyWealth> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyWealth> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.count4page", param);
        Page<MyWealth> page = new Page<MyWealth>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyWealth> page(MyWealthQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", query.getUserId());
        List<MyWealth> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.count4query", param);
        Page<MyWealth> page = new Page<MyWealth>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyWealth> listAll() {

        List<MyWealth> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.listAll");
        return list;
    }

    @Override
    public boolean updateLock(MyWealth myWealth) {

        Map<String, Object> map = BeanUtils.transBean2Map(myWealth);
        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.updateLock", map) > 0;
    }

    @Override
    public MyWealth getByUserId(long userId) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.getByUserId", userId);
    }

    @Override
    public boolean deleteByUser(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealth> listTop(int number, int type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("number", number);
        if (type == 1) {
            param.put("orderField", "commissionSummation");
        } else if (type == 2) {
            param.put("orderField", "consumptionCurrencySummation");
        } else if (type == 3) {
            param.put("orderField", "integralSummation");
        } else {
            param.put("orderField", "commissionSummation");
        }
        List<MyWealth> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthMapper.listTop", param);
        return list;
    }
}
