package com.qcloud.component.commoditycenter.dao.mysql;

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
import com.qcloud.component.commoditycenter.dao.MerchandiseDao;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.query.MerchandiseQuery;

@Repository
public class MerchandiseDaoMysqlImpl implements MerchandiseDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Merchandise merchandise) {

        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.insert", merchandise) == 1;
    }

    @Override
    public Merchandise get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Merchandise merchandise) {

        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.update", merchandise) > 0;
    }

    @Override
    public List<Merchandise> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Merchandise> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchandise> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.count4page", param);
        Page<Merchandise> page = new Page<Merchandise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Merchandise> page(MerchandiseQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", query.getMerchantId());
        param.put("name", StringUtil.nullToEmpty(query.getName()));
        param.put("merchantClassifyId", query.getMerchantClassifyId());
        param.put("code", StringUtil.emptyToNull(query.getCode()));
        param.put("specClassifyId", query.getSpecClassifyId());
        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.count4query", param);
        Page<Merchandise> page = new Page<Merchandise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Merchandise> listAll() {

        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.listAll");
        return list;
    }

    @Override
    public Page<Merchandise> page(Map<String, Object> map, int start, int count) {

        map.put("start", start);
        map.put("count", count);
        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.list4query2", map);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.count4query2", map);
        Page<Merchandise> merchandisePage = new Page<Merchandise>();
        merchandisePage.setCount(total);
        merchandisePage.setData(list);
        return merchandisePage;
    }

    @Override
    public List<Merchandise> merchandiseList(Map<String, Object> map) {

        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.listByMap", map);
        return list;
    }

    @Override
    public Page<Merchandise> list4MerchandiseState(Map<String, Object> param) {

        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.list4MerchandiseState", param);
        int totalCount = 0;
        Page<Merchandise> page = new Page<Merchandise>();
        page.setData(list);
        page.setCount(totalCount);
        return page;
    }

    @Override
    public boolean updateMerchandiseState(Map<String, Object> param) {

        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.updateMerchandiseState", param) > 0;
    }

    @Override
    public List<Merchandise> listByName(String name) {

        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.listByName", name);
        return list;
    }

    @Override
    public List<Merchandise> listBySysCode(String code) {

        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.listBySysCode", code);
        return list;
    }

    @Override
    public List<Merchandise> listByCode(Long merchantId, String code) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantId", merchantId);
        param.put("code", code);
        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.listByCode", param);
        return list;
    }

    @Override
    public int count4DeleteClassify(Long mallClassifyId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mallClassifyId", mallClassifyId);
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.count4Admin", param);
    }

    @Override
    public List<Merchandise> getMerchandiseList(long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantId", merchantId);
        return sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.getMerchandiseList", param);
    }

    @Override
    public int countMerchantOnlineNumber(long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantId", merchantId);
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.countMerchantOnlineNumber", param);
    }

    @Override
    public List<Merchandise> list4Admin(MerchandiseQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", query.getMerchantId());
        param.put("name", StringUtil.nullToEmpty(query.getName()));
        param.put("merchantClassifyId", query.getMerchantClassifyId());
        param.put("specClassifyId", query.getSpecClassifyId());
        param.put("mallClassifyId", query.getMallClassifyId());
        List<Merchandise> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.list4Admin", param);
        return list;
    }

    @Override
    public int count4Admin(MerchandiseQuery query) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantId", query.getMerchantId());
        param.put("name", StringUtil.nullToEmpty(query.getName()));
        param.put("merchantClassifyId", query.getMerchantClassifyId());
        param.put("specClassifyId", query.getSpecClassifyId());
        param.put("mallClassifyId", query.getMallClassifyId());
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseMapper.count4Admin", param);
    }
}
