package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantEvaluationDao;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.query.MerchantEvaluationQuery;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.TableSplitUtil;

@Repository
public class MerchantEvaluationDaoMysqlImpl implements MerchantEvaluationDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchantEvaluation merchantEvaluation) {

        Map<String, Object> param = BeanUtils.transBean2Map(merchantEvaluation);
        param.put("table_name", getTableName(merchantEvaluation.getMerchantId()));
        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.insert", param) == 1;
    }

    @Override
    public MerchantEvaluation get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchantEvaluation merchantEvaluation) {

        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.update", merchantEvaluation) > 0;
    }

    @Override
    public List<MerchantEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantEvaluation> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchantEvaluation> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.count4page", param);
        Page<MerchantEvaluation> page = new Page<MerchantEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchantEvaluation> page(MerchantEvaluationQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", query.getMerchantId());
        param.put("evaluationTime", DateUtil.date2String(query.getTime(), DateUtil.DATE_FORMAT_STRING));
        param.put("table_name", getTableName(query.getMerchantId()));
        List<MerchantEvaluation> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.count4query", param);
        Page<MerchantEvaluation> page = new Page<MerchantEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchantEvaluation> listAll() {

        List<MerchantEvaluation> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.listAll");
        return list;
    }

    private String getTableName(long merchantId) {

        return TableSplitUtil.getTableSplitName(merchantId, "sellercenter_merchant_evaluation", 100);
    }

    @Override
    public MerchantEvaluation get(long evaluationId, long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", 0);
        param.put("count", 1);
        param.put("evaluationId", evaluationId);
        param.put("table_name", getTableName(merchantId));
        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.getByEvaluationId", param);
    }

    @Override
    public boolean delete(long evaluationId, long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("evaluationId", evaluationId);
        param.put("table_name", getTableName(merchantId));
        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantEvaluationMapper.deleteByEvaluationId", param) > 0;
    }
}
