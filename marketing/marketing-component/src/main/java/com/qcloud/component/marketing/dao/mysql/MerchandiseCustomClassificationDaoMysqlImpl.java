package com.qcloud.component.marketing.dao.mysql;

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
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.component.marketing.dao.MerchandiseCustomClassificationDao;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;

@Repository
public class MerchandiseCustomClassificationDaoMysqlImpl implements MerchandiseCustomClassificationDao {

    @Resource(name = "sqlOperator-marketing")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseCustomClassification merchandiseCustomClassification) {

        return sqlOperator.insert("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.insert", merchandiseCustomClassification) == 1;
    }

    @Override
    public MerchandiseCustomClassification get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseCustomClassification merchandiseCustomClassification) {

        return sqlOperator.update("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.update", merchandiseCustomClassification) > 0;
    }

    @Override
    public List<MerchandiseCustomClassification> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseCustomClassification> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseCustomClassification> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseCustomClassification> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.count4page", param);
        Page<MerchandiseCustomClassification> page = new Page<MerchandiseCustomClassification>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseCustomClassification> page(MerchandiseCustomClassificationQuery query, int start, int count) {

        Map<String, Object> param = BeanUtils.transBean2Map(query);
        param.put("start", start);
        param.put("count", count);
        param.put("name",StringUtil.nullToEmpty(query.getName()));
        List<MerchandiseCustomClassification> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.count4query", param);
        Page<MerchandiseCustomClassification> page = new Page<MerchandiseCustomClassification>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseCustomClassification> listAll() {

        List<MerchandiseCustomClassification> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.listAll");
        return list;
    }

    @Override
    public List<MerchandiseCustomClassification> listAll(Map<String, Object> param) {

        List<MerchandiseCustomClassification> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.listAll2", param);
        return list;
    }

    @Override
    public boolean delete(Map<String, Object> map) {

        return sqlOperator.delete("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.deleteByMap", map) > 0;
    }

    @Override
    public List<MerchandiseCustomClassification> list(long merchantId, long customClassifyId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", merchantId);
        param.put("customClassifyId", customClassifyId);
        List<MerchandiseCustomClassification> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.MerchandiseCustomClassificationMapper.list", param);
        return list;
    }
}
