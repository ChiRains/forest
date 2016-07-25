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
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.util.TableSplitUtil;
import com.qcloud.component.personalcenter.dao.MyWealthDetailDao;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;

@Repository
public class MyWealthDetailDaoMysqlImpl implements MyWealthDetailDao {

    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyWealthDetail myWealthDetail) {

        Map<String, Object> param = BeanUtils.transBean2Map(myWealthDetail);
        param.put("table_name", getTableName(myWealthDetail.getUserId()));
        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.insert", param) == 1;
    }

    @Override
    public MyWealthDetail get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyWealthDetail myWealthDetail) {
        Map<String, Object> param = BeanUtils.transBean2Map(myWealthDetail);
        param.put("table_name", getTableName(myWealthDetail.getUserId()));
        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.update", param) > 0;
    }

    @Override
    public List<MyWealthDetail> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyWealthDetail> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyWealthDetail> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyWealthDetail> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.count4page", param);
        Page<MyWealthDetail> page = new Page<MyWealthDetail>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyWealthDetail> page(MyWealthDetailQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyWealthDetail> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.count4query", param);
        Page<MyWealthDetail> page = new Page<MyWealthDetail>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyWealthDetail> listAll() {

        List<MyWealthDetail> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.listAll");
        return list;
    }

    @Override
    public Page<MyWealthDetail> getWealthDetails(Long wealthId, Long userId, int type, int start, int size) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("wealthId", wealthId);
        param.put("userId", userId);
        param.put("type", type);
        param.put("start", start);
        param.put("count", size);
        List<MyWealthDetail> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.getDetailsList", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.getDetailsCount", param);
        Page<MyWealthDetail> page = new Page<MyWealthDetail>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    private String getTableName(Long userId) {

        return TableSplitUtil.getTableSplitName(userId, "personalcenter_my_wealth_detail", 100);
    }

    @Override
    public List<MyWealthDetail> listByUser(Long wealthId, Long userId, Integer type, Integer detailType, int start, int size) {

        detailType = detailType == null || detailType < 1 || detailType > 3 ? 1 : detailType;
        String pointSqlFragment = "";
        if (detailType == 2) {
            pointSqlFragment = " and point > 0 ";
        } else if (detailType == 3) {
            pointSqlFragment = " and point < 0 ";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("wealthId", wealthId);
        param.put("userId", userId);
        param.put("type", type);
        param.put("pointSqlFragment", pointSqlFragment);
        param.put("start", start);
        param.put("count", size);
        List<MyWealthDetail> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.listByUser", param);
        return list;
    }

    @Override
    public List<MyWealthDetail> listByTime(Long wealthId, Long userId, Integer type, Date begin, Date end) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("wealthId", wealthId);
        param.put("userId", userId);
        param.put("type", type);
        param.put("begin", begin);
        param.put("end", end);
        List<MyWealthDetail> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.listByTime", param);
        return list;
    }

    @Override
    public List<MyWealthDetail> listByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end, int start, int size) {

        detailType = detailType == null || detailType < 1 || detailType > 3 ? 1 : detailType;
        String pointSqlFragment = "";
        if (detailType == 2) {
            pointSqlFragment = " and point > 0 ";
        } else if (detailType == 3) {
            pointSqlFragment = " and point < 0 ";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("wealthId", wealthId);
        param.put("userId", userId);
        param.put("type", type);
        param.put("pointSqlFragment", pointSqlFragment);
        param.put("start", start);
        param.put("count", size);
        param.put("beginTime", begin != null ? DateUtil.date2String(begin) : begin);
        param.put("endTime", end != null ? DateUtil.date2String(end) : end);
        List<MyWealthDetail> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.listByUserAndTime", param);
        return list;
    }

    @Override
    public double sumByUserAndTime(long wealthId, long userId, int type, Integer detailType, Date begin, Date end, int start, int size) {

        detailType = detailType == null || detailType < 1 || detailType > 3 ? 1 : detailType;
        String pointSqlFragment = "";
        if (detailType == 2) {
            pointSqlFragment = " and point > 0 ";
        } else if (detailType == 3) {
            pointSqlFragment = " and point < 0 ";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("wealthId", wealthId);
        param.put("userId", userId);
        param.put("type", type);
        param.put("pointSqlFragment", pointSqlFragment);
        // 水漆使用, 刷新一次佣金总和
        param.put("start", 0);
        param.put("count", start + size);
        param.put("beginTime", begin != null ? DateUtil.date2String(begin) : begin);
        param.put("endTime", end != null ? DateUtil.date2String(end) : end);
        Double sum = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.sumByUserAndTime", param);
        return sum != null ? sum : 0;
    }

    @Override
    public MyWealthDetail getByIdandUserId(Long id, Long userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("id", id);
        param.put("userId", userId);
        MyWealthDetail myWealthDetail=sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.getByIdandUserId", param);
        return myWealthDetail;
    }

    @Override
    public int countByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end) {

        detailType = detailType == null || detailType < 1 || detailType > 3 ? 1 : detailType;
        String pointSqlFragment = "";
        if (detailType == 2) {
            pointSqlFragment = " and point > 0 ";
        } else if (detailType == 3) {
            pointSqlFragment = " and point < 0 ";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("wealthId", wealthId);
        param.put("userId", userId);
        param.put("type", type);
        param.put("pointSqlFragment", pointSqlFragment);
        param.put("beginTime", begin != null ? DateUtil.date2String(begin) : begin);
        param.put("endTime", end != null ? DateUtil.date2String(end) : end);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MyWealthDetailMapper.countByUserAndTime", param);
        return total;
    }
}
