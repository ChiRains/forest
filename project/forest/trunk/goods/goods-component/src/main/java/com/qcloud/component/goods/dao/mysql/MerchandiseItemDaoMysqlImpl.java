//package com.qcloud.component.goods.dao.mysql;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import javax.annotation.Resource;
//import org.apache.commons.lang.NotImplementedException;
//import org.springframework.stereotype.Repository;
//import com.qcloud.component.goods.dao.MerchandiseItemDao;
//import com.qcloud.component.goods.model.MerchandiseItem;
//import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
//import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
//import com.qcloud.component.goods.model.key.TypeEnum.QueryType;
//import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
//import com.qcloud.pirates.core.reflect.BeanUtils;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
//
//@Repository
//public class MerchandiseItemDaoMysqlImpl implements MerchandiseItemDao {
//
//    @Resource(name = "sqlOperator-goods")
//    private SqlOperator sqlOperator;
//
//    @Override
//    public boolean add(MerchandiseItem merchandiseItem) {
//
//        return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.insert", merchandiseItem) == 1;
//    }
//
//    @Override
//    public MerchandiseItem get(Long id) {
//
//        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.get", id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.delete", id) > 0;
//    }
//
//    @Override
//    public boolean update(MerchandiseItem merchandiseItem) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public List<MerchandiseItem> list(List<Long> idList) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Map<Long, MerchandiseItem> map(Set<Long> idSet) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> page(int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4page", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4page", param);
//        Page<MerchandiseItem> page = new Page<MerchandiseItem>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        param.put("name", query.getName());
//        param.put("merchantId", query.getMerchantId());
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4query", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4query", param);
//        Page<MerchandiseItem> page = new Page<MerchandiseItem>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public List<MerchandiseItem> listAll() {
//
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.listAll");
//        return list;
//    }
//
//    @Override
//    public MerchandiseItem getByUnifiedMerchandise(Long unifiedMerchandiseId) {
//
//        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.getByUnifiedMerchandise", unifiedMerchandiseId);
//    }
//
//    @Override
//    public List<MerchandiseItem> list(Map where) {
//
//        return sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list", where);
//    }
//
//    @Override
//    public MerchandiseItem get(Map where) {
//
//        where.put("start", 0);
//        where.put("count", 1);
//        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list", where);
//    }
//
//    @Override
//    public Page<MerchandiseItem> page(Map where, int start, int size) {
//
//        where.put("start", start);
//        where.put("count", size);
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list", where);
//        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count", where);
//        Page<MerchandiseItem> merchandiseItemPage = new Page<MerchandiseItem>();
//        merchandiseItemPage.setCount(total);
//        merchandiseItemPage.setData(list);
//        return merchandiseItemPage;
//    }
//
//    @Override
//    public List<MerchandiseItem> listByMerchandise(Long merchandiseId) {
//
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.listByMerchandise", merchandiseId);
//        return list;
//    }
//
//    @Override
//    public Page<MerchandiseItem> page4Price(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", name);
//        param.put("merchantId", merchantId);
//        param.put("orderField", "discount");
//        param.put("orderType", orderType.getKey() == OrderType.ASE.getKey() ? "" : "desc");
//        param.put("queryType", queryItemType.getKey());
//        param.put("merchantClassifyId", merchantClassifyId);
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4Front", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4Front", param);
//        Page<MerchandiseItem> page = new Page<MerchandiseItem>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public Page<MerchandiseItem> page4Date(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", size);
//        param.put("name", name);
//        param.put("merchantId", merchantId);
//        param.put("orderField", "recordTime");
//        param.put("orderType", orderType.getKey() == OrderType.ASE.getKey() ? "" : "desc");
//        param.put("queryType", queryItemType.getKey());
//        param.put("merchantClassifyId", merchantClassifyId);
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4Front", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4Front", param);
//        Page<MerchandiseItem> page = new Page<MerchandiseItem>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public MerchandiseItem getBySpecifications(Long merchandiseId, Long merchandiseSpecificationsId) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("merchandiseId", merchandiseId);
//        param.put("merchandiseSpecificationsId", merchandiseSpecificationsId);
//        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.getBySpecifications", param);
//    }
//
//    @Override
//    public boolean update(MerchandiseItem merchandiseItem, Date lastUpdateTime) {
//
//        Map<String, Object> map = BeanUtils.transBean2Map(merchandiseItem);
//        map.put("lastUpdateTime", lastUpdateTime);
//        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.update", map) > 0;
//    }
//
//    @Override
//    public Page<MerchandiseItem> query(MerchandiseItemQuery query, int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        param.put("name", query.getName());
//        param.put("merchantId", query.getMerchantId());
//        QueryType queryType = query.getQueryType();
//        if (QueryType.SALES_VOLUME.getKey() == queryType.getKey()) { // 销量
//            param.put("orderField", "salesVolume");
//        } else if (QueryType.DATE.getKey() == queryType.getKey()) {// 日期
//            param.put("orderField", "recordTime");
//        } else if (QueryType.PRICE.getKey() == queryType.getKey()) {// 价格
//            param.put("orderField", "discount");
//        } else if (QueryType.HOT.getKey() == queryType.getKey()) {// 热度
//            param.put("orderField", "clickRate");
//        } else if (QueryType.GOODEVALUATION.getKey() == queryType.getKey()) {// 好评
//            param.put("orderField", "goodEvaluation");
//        }
//        param.put("orderType", query.getOrderType().getKey() == OrderType.ASE.getKey() ? "" : "desc");
//        param.put("queryType", query.getQueryItemType().getKey());
//        param.put("merchantClassifyId", query.getMerchantClassifyId());
//        param.put("mallClassifyId", query.getMallClassifyId());
//        param.put("merchantClassifyBsid", query.getMerchantClassifyBsid());
//        param.put("mallClassifyBsid", query.getMallClassifyBsid());
//        param.put("brandId", query.getBrandId());
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4Front", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4Front", param);
//        Page<MerchandiseItem> page = new Page<MerchandiseItem>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public Page<MerchandiseItem> list4Select4Admin(MerchandiseItemQuery query, int start, int count) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("start", start);
//        param.put("count", count);
//        param.put("name", query.getName());
//        param.put("merchantId", query.getMerchantId());
//        param.put("merchantClassifyId", query.getMerchantClassifyId());
//        param.put("mallClassifyId", query.getMallClassifyId());
//        param.put("unifiedMerchandiseId", query.getUnifiedMerchandiseId());
//        List<MerchandiseItem> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4Select4Admin", param);
//        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4Select4Admin", param);
//        Page<MerchandiseItem> page = new Page<MerchandiseItem>();
//        page.setCount(total);
//        page.setData(list);
//        return page;
//    }
//
//    @Override
//    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("merchantId", merchantId);
//        return sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.merchandiseListByMerchantId", param);
//    }
//}
