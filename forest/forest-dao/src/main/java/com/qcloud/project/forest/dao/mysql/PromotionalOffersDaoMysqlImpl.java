package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.PromotionalOffersDao;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;

@Repository
public class PromotionalOffersDaoMysqlImpl implements PromotionalOffersDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(PromotionalOffers promotionalOffers) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.insert", promotionalOffers) == 1;
    }

    @Override
    public PromotionalOffers get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.delete", id) > 0;
    }

    @Override
    public boolean update(PromotionalOffers promotionalOffers) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.update", promotionalOffers) > 0;
    }

    @Override
    public List<PromotionalOffers> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, PromotionalOffers> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<PromotionalOffers> listByClassify(Long classify) {

        throw new NotImplementedException();
    }

    @Override
    public Page<PromotionalOffers> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<PromotionalOffers> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.count4page", param);
        Page<PromotionalOffers> page = new Page<PromotionalOffers>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<PromotionalOffers> page(PromotionalOffersQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("classify", query.getClassify());
        List<PromotionalOffers> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.count4query", param);
        Page<PromotionalOffers> page = new Page<PromotionalOffers>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<PromotionalOffers> listAll() {

        List<PromotionalOffers> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.PromotionalOffersMapper.listAll");
        return list;
    }
}
