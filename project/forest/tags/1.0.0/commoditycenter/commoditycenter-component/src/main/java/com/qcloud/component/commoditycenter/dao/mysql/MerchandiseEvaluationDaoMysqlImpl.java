package com.qcloud.component.commoditycenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.qcloud.component.commoditycenter.dao.MerchandiseEvaluationDao;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseEvaluationQuery;
import com.qcloud.component.sellercenter.model.key.TypeEnum.StatusType;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.TableSplitUtil;

@Repository
public class MerchandiseEvaluationDaoMysqlImpl implements MerchandiseEvaluationDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseEvaluation merchandiseEvaluation) {

        Map<String, Object> param = BeanUtils.transBean2Map(merchandiseEvaluation);
        param.put("table_name", getTableName(merchandiseEvaluation.getMerchandiseId()));
        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.insert", param) == 1;
    }

    @Override
    public MerchandiseEvaluation get(Long evaluationId, Long merchandiseId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", evaluationId);
        map.put("table_name", getTableName(merchandiseId));
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.get", map);
    }

    @Override
    public boolean delete(Long id, Long merchandiseId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(merchandiseId));
        param.put("id", id);
        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.deleteByMerchandiseId", param) > 0;
    }

    @Override
    public boolean update(MerchandiseEvaluation merchandiseEvaluation) {

        Map<String, Object> param = BeanUtils.transBean2Map(merchandiseEvaluation);
        param.put("table_name", getTableName(merchandiseEvaluation.getMerchandiseId()));
        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.update", param) > 0;
    }

    @Override
    public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count) {

        StarLevelType starLevelType = query.getStarLevelType();
        int key = starLevelType.getKey();
        int star = starLevelType.getKey();
        int nextStar = 0;
        if (key == StarLevelType.CP.getKey()) {
            nextStar = StarLevelType.ZP.getKey();
        } else if (key == StarLevelType.ZP.getKey()) {
            nextStar = StarLevelType.HP.getKey();
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchandiseId", query.getMerchandiseId());
        param.put("star", star);
        param.put("nextStar", nextStar);
        param.put("table_name", getTableName(query.getMerchandiseId()));
        List<MerchandiseEvaluation> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.count4query", param);
        Page<MerchandiseEvaluation> page = new Page<MerchandiseEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    private String getTableName(long merchandiseId) {

        return TableSplitUtil.getTableSplitName(merchandiseId, "commoditycenter_merchandise_evaluation", 100);
    }

    @Override
    public Page<MerchandiseEvaluation> page(long merchandiseId, StarLevelType starLevelType, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("table_name", getTableName(merchandiseId));
        param.put("merchandiseId", merchandiseId);
        param.put("status", StatusType.PASS.getKey());
        int star = starLevelType.getKey();
        int nextStar = 0;
        if (star == StarLevelType.CP.getKey()) {
            nextStar = StarLevelType.ZP.getKey();
        } else if (star == StarLevelType.ZP.getKey()) {
            nextStar = StarLevelType.HP.getKey();
        }
        param.put("star", star);
        param.put("nextStar", nextStar);
        List<MerchandiseEvaluation> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.list", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseEvaluationMapper.count", param);
        Page<MerchandiseEvaluation> page = new Page<MerchandiseEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }
}
