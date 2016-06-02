package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.sellercenter.dao.MerchantMemberDao;
import com.qcloud.component.sellercenter.model.MerchantMember;
import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;

@Repository
public class MerchantMemberDaoMysqlImpl implements MerchantMemberDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchantMember merchantMember) {

        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.insert", merchantMember) == 1;
    }

    @Override
    public MerchantMember get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchantMember merchantMember) {

        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.update", merchantMember) > 0;
    }

    @Override
    public List<MerchantMember> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantMember> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantMember> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchantMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.count4page", param);
        Page<MerchantMember> page = new Page<MerchantMember>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchantMember> page(MerchantMemberQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", query.getMerchantId());
        List<MerchantMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.count4query", param);
        Page<MerchantMember> page = new Page<MerchantMember>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchantMember> listAll() {

        List<MerchantMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.listAll");
        return list;
    }

    @Override
    public List<MerchantMember> listByMerchant(Long merchantId) {

        List<MerchantMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.listByMerchant", merchantId);
        return list;
    }

    @Override
    public MerchantMember get(Long memberId, Long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("memberId", memberId);
        param.put("merchantId", merchantId);
        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.getByMemberAndMerchant", param);
    }

    @Override
    public List<MerchantMember> listByMember(Long memberId) {

        List<MerchantMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMemberMapper.listByMember", memberId);
        return list;
    }
}
