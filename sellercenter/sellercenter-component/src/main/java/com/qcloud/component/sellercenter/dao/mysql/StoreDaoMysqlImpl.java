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
//import com.qcloud.pirates.util.StringUtil;
//import com.qcloud.component.sellercenter.dao.StoreDao;
//import com.qcloud.component.sellercenter.model.Store;
//import com.qcloud.component.sellercenter.model.query.StoreQuery;
//
//@Repository
//public class StoreDaoMysqlImpl implements StoreDao {
//
//    @Resource(name = "sqlOperator-sellercenter")
//    private SqlOperator sqlOperator;
//
//    @Override
//    public boolean add(Store store) {
//
//        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.insert", store) == 1;
//    }
//
//    @Override
//    public Store get(Long id) {
//
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.get", id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.delete", id) > 0;
//    }
//
//    @Override
//    public boolean update(Store store) {
//
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.update", store) > 0;
//    }
//
//    @Override
//    public List<Store> list(List<Long> idList) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Map<Long, Store> map(Set<Long> idSet) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<Store> page(int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        List<Store> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.list4page", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.count4page", param);
//        Page<Store> page = new Page<Store>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public Page<Store> page(StoreQuery query, int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        param.put("merchantId", query.getMerchantId());
//        param.put("name", query.getName());
//        List<Store> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.list4query", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.count4query", param);
//        Page<Store> page = new Page<Store>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public List<Store> listAll() {
//
//        List<Store> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.listAll");
//        return list;
//    }
//
//    @Override
//    public List<Store> listChildren(Long parentId) {
//
//        List<Store> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.listChildren", parentId);
//        return list;
//    }
//
//    @Override
//    public List<Store> listByMerchant(Long merchantId) {
//
//        List<Store> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.listByMerchant", merchantId);
//        return list;
//    }
//
//    @Override
//    public List<Store> listByAddress(String province, String city, String district) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("province", StringUtil.nullToEmpty(province));
//        param.put("city", StringUtil.nullToEmpty(city));
//        param.put("district", StringUtil.nullToEmpty(district));
//        List<Store> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.listByAddress", param);
//        return list;
//    }
//
//    @Override
//    public List<Store> listByParentId(Long parentId) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("parentId", parentId);
//        List<Store> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMapper.listByParentId", param);
//        return list;
//    }
//}
