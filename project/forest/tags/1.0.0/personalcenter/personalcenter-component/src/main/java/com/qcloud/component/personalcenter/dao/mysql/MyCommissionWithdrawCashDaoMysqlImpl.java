package com.qcloud.component.personalcenter.dao.mysql;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.component.personalcenter.dao.MyCommissionWithdrawCashDao;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;

@Repository
public class MyCommissionWithdrawCashDaoMysqlImpl implements MyCommissionWithdrawCashDao {

    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyCommissionWithdrawCash myCommissionWithdrawCash) {

        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.insert", myCommissionWithdrawCash) == 1;
    }

    @Override
    public MyCommissionWithdrawCash get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyCommissionWithdrawCash myCommissionWithdrawCash) {

        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.update", myCommissionWithdrawCash) > 0;
    }

    @Override
    public List<MyCommissionWithdrawCash> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCommissionWithdrawCash> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCommissionWithdrawCash> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyCommissionWithdrawCash> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.count4page", param);
        Page<MyCommissionWithdrawCash> page = new Page<MyCommissionWithdrawCash>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyCommissionWithdrawCash> page(MyCommissionWithdrawCashQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        String date="";
        if (query.getDate()!= null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             date = sdf.format(query.getDate());
        }
        else
        {
            date=null;
        }
        param.put("mode", query.getMode());
        param.put("date", date + "%");
        param.put("state", query.getState());
        List<MyCommissionWithdrawCash> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.count4query", param);
        Page<MyCommissionWithdrawCash> page = new Page<MyCommissionWithdrawCash>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyCommissionWithdrawCash> listAll() {

        List<MyCommissionWithdrawCash> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.listAll");
        return list;
    }

    @Override
    public List<MyCommissionWithdrawCash> listByUser(Long userId, String checkTime, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        param.put("checkTime", StringUtil.emptyToNull(checkTime));
        List<MyCommissionWithdrawCash> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.listByUser", param);
        return list;
    }

    @Override
    public double statWithdrawingCommission(Long userId) {

        Double withdrawing = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.statWithdrawingCommission", userId);
        return withdrawing == null ? 0.0 : withdrawing;
    }

    @Override
    public double statWithdrawedCommission(Long userId) {

        Double withdrawed = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.statWithdrawedCommission", userId);
        return withdrawed == null ? 0.0 : withdrawed;
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawingByUser(Long userId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        List<MyCommissionWithdrawCash> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.listWithdrawingByUser", param);
        return list;
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawedByUser(Long userId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        List<MyCommissionWithdrawCash> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyCommissionWithdrawCashMapper.listWithdrawedByUser", param);
        return list;
    }
}
