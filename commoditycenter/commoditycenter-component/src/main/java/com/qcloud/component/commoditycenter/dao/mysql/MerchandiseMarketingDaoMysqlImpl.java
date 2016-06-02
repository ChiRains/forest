package com.qcloud.component.commoditycenter.dao.mysql;

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
import com.qcloud.component.commoditycenter.dao.MerchandiseMarketingDao;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.query.MerchandiseMarketingQuery;

@Repository
public class MerchandiseMarketingDaoMysqlImpl implements MerchandiseMarketingDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseMarketing merchandiseMarketing) {

        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.insert", merchandiseMarketing) == 1;
    }

    @Override
    public MerchandiseMarketing get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseMarketing merchandiseMarketing) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseMarketing> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseMarketing> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseMarketing> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseMarketing> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.count4page", param);
        Page<MerchandiseMarketing> page = new Page<MerchandiseMarketing>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("activityId", query.getActivityId());
        param.put("sence", query.getSence());
        param.put("name", query.getName());
        
        List<MerchandiseMarketing> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.count4query", param);
        Page<MerchandiseMarketing> page = new Page<MerchandiseMarketing>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseMarketing> listAll() {

        List<MerchandiseMarketing> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.listAll");
        return list;
    }

    @Override
    public MerchandiseMarketing getByUnifiedMerchandise(Long unifiedMerchandiseId) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.getByUnifiedMerchandise", unifiedMerchandiseId);
    }

    @Override
    public boolean update(MerchandiseMarketing merchandiseMarketing, Date lastUpdateTime) {

        Map<String, Object> map = BeanUtils.transBean2Map(merchandiseMarketing);
        map.put("lastUpdateTime", lastUpdateTime);
        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.update", map) > 0;
    }

    @Override
    public boolean setEnable(Long id,int enable) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("enable", enable);
        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.setEnable", map) > 0;
        
    }

    @Override
    public List<MerchandiseMarketing> list(int sence, double discountRange, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("discountRange", discountRange);
        param.put("sence", sence);               
        List<MerchandiseMarketing> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMarketingMapper.list", param);       
        return list;
    }
}
