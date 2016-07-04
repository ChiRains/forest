//package com.qcloud.component.sellercenter.dao.mysql;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import javax.annotation.Resource;
//import org.springframework.stereotype.Repository;
//import org.apache.commons.lang.NotImplementedException;
//import com.qcloud.pirates.core.reflect.BeanUtils;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
//import com.qcloud.component.sellercenter.dao.MerchantWealthDao;
//import com.qcloud.component.sellercenter.model.MerchantWealth;
//import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;
//
//@Repository
//public class MerchantWealthDaoMysqlImpl implements MerchantWealthDao {
//
//    @Resource(name = "sqlOperator-sellercenter")
//    private SqlOperator sqlOperator;
//
//    @Override
//    public boolean add(MerchantWealth merchantWealth) {
//
//        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.insert", merchantWealth) == 1;
//    }
//
//    @Override
//    public MerchantWealth get(Long id) {
//
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.get", id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.delete", id) > 0;
//    }
//
//    @Override
//    public boolean update(MerchantWealth merchantWealth) {
//
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.update", merchantWealth) > 0;
//    }
//
//    @Override
//    public List<MerchantWealth> list(List<Long> idList) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Map<Long, MerchantWealth> map(Set<Long> idSet) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchantWealth> page(int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        List<MerchantWealth> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.list4page", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.count4page", param);
//        Page<MerchantWealth> page = new Page<MerchantWealth>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public Page<MerchantWealth> page(MerchantWealthQuery query, int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        List<MerchantWealth> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.list4query", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.count4query", param);
//        Page<MerchantWealth> page = new Page<MerchantWealth>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public List<MerchantWealth> listAll() {
//
//        List<MerchantWealth> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.listAll");
//        return list;
//    }
//
//    @Override
//    public MerchantWealth getByMerchant(long merchantId) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("merchantId", merchantId);
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.getByMerchant", param);
//    }
//
//    @Override
//    public boolean updateLock(MerchantWealth merchantWealth) {
//
//        Map<String, Object> param = BeanUtils.transBean2Map(merchantWealth);
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthMapper.updateLock", param) > 0;
//    }
//}
