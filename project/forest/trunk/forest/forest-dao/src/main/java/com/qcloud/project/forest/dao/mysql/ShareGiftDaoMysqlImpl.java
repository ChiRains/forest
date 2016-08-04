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
import com.qcloud.project.forest.dao.ShareGiftDao;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.query.ShareGiftQuery;

@Repository
public class ShareGiftDaoMysqlImpl implements ShareGiftDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ShareGift shareGift) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.insert", shareGift) == 1;
    }

    @Override
    public ShareGift get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ShareGift shareGift) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.update", shareGift) > 0;
    }

    @Override
    public List<ShareGift> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ShareGift> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ShareGift> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ShareGift> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.count4page", param);
        Page<ShareGift> page = new Page<ShareGift>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ShareGift> page(ShareGiftQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ShareGift> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.count4query", param);
        Page<ShareGift> page = new Page<ShareGift>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ShareGift> listAll() {

        List<ShareGift> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.listAll");
        return list;
    }

    @Override
    public ShareGift getByUserId(Long userId) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftMapper.getByUserId", userId);
    }
}
