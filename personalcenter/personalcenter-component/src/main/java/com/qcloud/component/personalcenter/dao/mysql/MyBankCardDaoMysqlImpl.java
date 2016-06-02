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
import com.qcloud.component.personalcenter.dao.MyBankCardDao;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;

@Repository
public class MyBankCardDaoMysqlImpl implements MyBankCardDao {

    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyBankCard myBankCard) {

        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.insert", myBankCard) == 1;
    }

    @Override
    public MyBankCard get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyBankCard myBankCard) {

        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.update", myBankCard) > 0;
    }

    @Override
    public List<MyBankCard> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyBankCard> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyBankCard> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyBankCard> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.count4page", param);
        Page<MyBankCard> page = new Page<MyBankCard>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyBankCard> page(MyBankCardQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyBankCard> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.count4query", param);
        Page<MyBankCard> page = new Page<MyBankCard>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyBankCard> listAll() {

        List<MyBankCard> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.listAll");
        return list;
    }

    @Override
    public List<MyBankCard> listByUser(long userId) {

        List<MyBankCard> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyBankCardMapper.listByUser", userId);
        return list;
    }
}
