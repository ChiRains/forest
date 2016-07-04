//package com.qcloud.component.sellercenter.dao.mysql;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import javax.annotation.Resource;
//import org.springframework.stereotype.Repository;
//import org.apache.commons.lang.NotImplementedException;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
//import com.qcloud.component.sellercenter.dao.MerchantSpecClassifyDao;
//import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
//import com.qcloud.component.sellercenter.model.query.MerchantSpecClassifyQuery;
//
//@Repository
//public class MerchantSpecClassifyDaoMysqlImpl implements MerchantSpecClassifyDao {
//
//    @Resource(name = "sqlOperator-sellercenter")
//    private SqlOperator sqlOperator;
//
//    @Override
//    public boolean add(MerchantSpecClassify merchantSpecClassify) {
//
//        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.insert", merchantSpecClassify) == 1;
//    }
//
//    @Override
//    public MerchantSpecClassify get(Long id) {
//
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.get", id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.delete", id) > 0;
//    }
//
//    @Override
//    public boolean update(MerchantSpecClassify merchantSpecClassify) {
//
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.update", merchantSpecClassify) > 0;
//    }
//
//    @Override
//    public List<MerchantSpecClassify> list(List<Long> idList) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Map<Long, MerchantSpecClassify> map(Set<Long> idSet) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchantSpecClassify> page(int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        List<MerchantSpecClassify> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.list4page", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.count4page", param);
//        Page<MerchantSpecClassify> page = new Page<MerchantSpecClassify>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public Page<MerchantSpecClassify> page(MerchantSpecClassifyQuery query, int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        List<MerchantSpecClassify> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.list4query", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.count4query", param);
//        Page<MerchantSpecClassify> page = new Page<MerchantSpecClassify>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public List<MerchantSpecClassify> listAll() {
//
//        List<MerchantSpecClassify> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.listAll");
//        return list;
//    }
//
//    @Override
//    public List<MerchantSpecClassify> listByMerchant(long merchantId) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("merchantId", merchantId);
//        List<MerchantSpecClassify> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSpecClassifyMapper.listByMerchant", param);
//        return list;
//    }
//}
