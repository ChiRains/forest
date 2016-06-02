package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.DistributeMembershipCardDao;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.DistributeMembershipCardStat;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardQuery;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardStatQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class DistributeMembershipCardDaoMysqlImpl implements DistributeMembershipCardDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(DistributeMembershipCard distributeMembershipCard) {

        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.insert", distributeMembershipCard) == 1;
    }

    @Override
    public DistributeMembershipCard get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.delete", id) > 0;
    }

    @Override
    public boolean update(DistributeMembershipCard distributeMembershipCard) {

        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.update", distributeMembershipCard) > 0;
    }

    @Override
    public List<DistributeMembershipCard> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, DistributeMembershipCard> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<DistributeMembershipCard> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DistributeMembershipCard> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.count4page", param);
        Page<DistributeMembershipCard> page = new Page<DistributeMembershipCard>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<DistributeMembershipCard> page(DistributeMembershipCardQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DistributeMembershipCard> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.count4query", param);
        Page<DistributeMembershipCard> page = new Page<DistributeMembershipCard>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<DistributeMembershipCard> listAll() {

        List<DistributeMembershipCard> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.listAll");
        return list;
    }

    @Override
    public DistributeMembershipCard getByCardNumber(String cardNumber) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.getByCardNumber", cardNumber);
    }

    @Override
    public Page<DistributeMembershipCardStat> statPage(DistributeMembershipCardStatQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantCode", query.getMerchantCode());
        param.put("merchantName", query.getMerchantName());
        param.put("start", start);
        param.put("count", count);
        List<DistributeMembershipCardStat> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.list4stat", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.count4stat", param);
        Page<DistributeMembershipCardStat> page = new Page<DistributeMembershipCardStat>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<DistributeMembershipCard> page(Long merchantId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", merchantId);
        List<DistributeMembershipCard> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.list4merchant", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.count4merchant", param);
        Page<DistributeMembershipCard> page = new Page<DistributeMembershipCard>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public int countMerchantSended(long merchantId) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.DistributeMembershipCardMapper.countMerchantSended", merchantId);
    }
}
