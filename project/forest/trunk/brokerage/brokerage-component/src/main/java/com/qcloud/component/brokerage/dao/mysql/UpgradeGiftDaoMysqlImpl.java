package com.qcloud.component.brokerage.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.brokerage.dao.UpgradeGiftDao;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;

@Repository
public class UpgradeGiftDaoMysqlImpl implements UpgradeGiftDao {

    @Resource(name = "sqlOperator-brokerage")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(UpgradeGift upgradeGift) {

        return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.insert", upgradeGift) == 1;
    }

    @Override
    public UpgradeGift get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.delete", id) > 0;
    }

    @Override
    public boolean update(UpgradeGift upgradeGift) {

        return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.update", upgradeGift) > 0;
    }

    @Override
    public List<UpgradeGift> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UpgradeGift> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UpgradeGift> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UpgradeGift> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.count4page", param);
        Page<UpgradeGift> page = new Page<UpgradeGift>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UpgradeGift> page(UpgradeGiftQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("gradeId", query.getGradeId());
        List<UpgradeGift> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.count4query", param);
        Page<UpgradeGift> page = new Page<UpgradeGift>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<UpgradeGift> listAll() {

        List<UpgradeGift> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.listAll");
        return list;
    }

    @Override
    public List<UpgradeGift> listCanGift(long gradeId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gradeId", gradeId);
        List<UpgradeGift> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeGiftMapper.listCanGift", param);
        return list;
    }
}
