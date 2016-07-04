//package com.qcloud.component.sellercenter.dao.mysql;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import javax.annotation.Resource;
//import org.apache.commons.lang.NotImplementedException;
//import org.springframework.stereotype.Repository;
//import com.qcloud.component.sellercenter.dao.MerchantDao;
//import com.qcloud.component.sellercenter.model.Merchant;
//import com.qcloud.component.sellercenter.model.query.MerchantQuery;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.LatitudeLontitudeUtil;
//import com.qcloud.pirates.util.LatitudeLontitudeUtil.Location;
//import com.qcloud.pirates.util.StringUtil;
//
//@Repository
//public class MerchantDaoMysqlImpl implements MerchantDao {
//
//    @Resource(name = "sqlOperator-sellercenter")
//    private SqlOperator sqlOperator;
//
//    @Override
//    public boolean add(Merchant merchant) {
//
//        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.insert", merchant) == 1;
//    }
//
//    @Override
//    public Merchant get(Long id) {
//
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.get", id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.delete", id) > 0;
//    }
//
//    @Override
//    public boolean update(Merchant merchant) {
//
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.update", merchant) > 0;
//    }
//
//    @Override
//    public List<Merchant> list(List<Long> idList) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Map<Long, Merchant> map(Set<Long> idSet) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<Merchant> page(int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.list4page", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.count4page", param);
//        Page<Merchant> page = new Page<Merchant>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public Page<Merchant> page(MerchantQuery query, int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        param.put("code", query.getCode());
//        param.put("admin", query.getAdmin());
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.list4query", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.count4query", param);
//        Page<Merchant> page = new Page<Merchant>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public List<Merchant> listAll() {
//
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.listAll");
//        return list;
//    }
//
//    // 以下查询要从当前经纬度,和距离算出四个点,然后在数据库进行比对
//    @Override
//    public List<Merchant> list(MerchantQuery query, int start, int size) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.list", param);
//        return list;
//    }
//
//    @Override
//    public List<Merchant> listNew(MerchantQuery query, int start, int size) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.listNew", param);
//        return list;
//    }
//
//    @Override
//    public List<Merchant> listHot(MerchantQuery query, int start, int size) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.listHot", param);
//        return list;
//    }
//
//    @Override
//    public List<Merchant> listRecently(MerchantQuery query, int start, int size) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        fillLatitudeLontitudeParam(query, param);
////        param.put("latitude", query.getLatitude());
////        param.put("longitude", query.getLongitude());
////        param.put("distances", query.getDistance()*1000);
//        param.put("classifyId", query.getClassifyId());
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.listRecently", param);
//        return list;
//    }
//
//    @Override
//    public List<Merchant> listFavourable(MerchantQuery query, int start, int size) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        List<Merchant> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.listFavourable", param);
//        return list;
//    }
//
//    @Override
//    public int count(MerchantQuery query) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.count", param);
//        return total;
//    }
//
//    @Override
//    public int countNew(MerchantQuery query) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.countNew", param);
//        return total;
//    }
//
//    @Override
//    public int countHot(MerchantQuery query) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.countHot", param);
//        return total;
//    }
//
//    @Override
//    public int countRecently(MerchantQuery query) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.countRecently", param);
//        return total;
//    }
//
//    @Override
//    public int countFavourable(MerchantQuery query) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        fillLatitudeLontitudeParam(query, param);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        param.put("classifyId", query.getClassifyId());
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.countFavourable", param);
//        return total;
//    }
//
//    private void fillLatitudeLontitudeParam(MerchantQuery query, Map<String, Object> param) {
//
//        if (query.getDistance() <= 0) {
//            param.put("distance", -1);
//            param.put("minLongitude", -1);
//            param.put("minLatitude", -1);
//            param.put("maxLongitude", -1);
//            param.put("maxLatitude", -1);
//        } else {
//            param.put("distance", query.getDistance());
//            AssertUtil.assertNotNull(query.getLatitude(), "按距离查询,经度不能为空.");
//            AssertUtil.assertNotNull(query.getLongitude(), "按距离查询,纬度不能为空.");
//            double lat = query.getLatitude();
//            double lng = query.getLongitude();
//            Location[] locations = LatitudeLontitudeUtil.getRectangle4Point(lat, lng, query.getDistance());
//            param.put("minLongitude", locations[1].getLongitude());
//            param.put("minLatitude", locations[2].getLatitude());
//            param.put("maxLongitude", locations[0].getLongitude());
//            param.put("maxLatitude", locations[0].getLatitude());
//        }
//    }
//
//    // 以下查询要从当前经纬度,和距离算出四个点,然后在数据库进行比对
//    @Override
//    public Page<Merchant> listNeedAudit(String keyword, int start, int size) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", size);
//        if (keyword != null && !keyword.equals("")) {
//            param.put("keyword", "%" + keyword + "%");
//        } else {
//            param.put("keyword", "%" + "%");
//        }
//        List<Merchant> merchants = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.listNeedAudit", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.listNeedAuditCount", param);
//        Page<Merchant> list = new Page<Merchant>();
//        list.setData(merchants);
//        list.setCount(total);
//        return list;
//    }
//
//    @Override
//    public Page<Merchant> pageMerchant(MerchantQuery query, int start, int size) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", StringUtil.nullToEmpty(query.getName()));
//        List<Merchant> merchants = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.list4Merchant", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.count4Merchant", param);
//        Page<Merchant> list = new Page<Merchant>();
//        list.setCount(total);
//        list.setData(merchants);
//        return list;
//    }
//
//    @Override
//    public boolean disableMerchant(long id) {
//        Map<String, Object> param=new HashMap<String, Object>();
//        param.put("id", id );
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.disableMerchant", param) > 0;
//    }
//
//    @Override
//    public boolean enableMerchant(long id) {
//        Map<String, Object> param=new HashMap<String, Object>();
//        param.put("id", id );
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.enableMerchant", param) > 0;
//    }
//    
//    
//
//    @Override
//    public int count4DeleteByClassifyId(Long classifyId) {
//
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.count4DeleteByClassifyId", classifyId);
//    }
//
//    @Override
//    public boolean updateMerchantNotify(long id,int notify) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("id", id);
//        param.put("notify", notify);
//        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.updateMerchantNotify",param)>0;
//    }
//
//    @Override
//    public Merchant getByCode(String code) {
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.getByCode", code);
//    }
//
//    @Override
//    public int countByName(String name) {
//
//        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMapper.countByName", name);
//        return total;
//    }
//}
