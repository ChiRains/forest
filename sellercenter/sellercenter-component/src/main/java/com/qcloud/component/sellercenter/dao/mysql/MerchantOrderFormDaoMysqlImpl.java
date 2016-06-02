package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantOrderFormDao;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.TableSplitUtil;

@Repository
public class MerchantOrderFormDaoMysqlImpl implements MerchantOrderFormDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchantOrderForm merchantOrderForm) {

        Map<String, Object> param = BeanUtils.transBean2Map(merchantOrderForm);
        param.put("table_name", getTableName(merchantOrderForm.getMerchantId()));
        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.insert", param) == 1;
    }

    @Override
    public MerchantOrderForm get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchantOrderForm merchantOrderForm) {

        Map<String, Object> param = BeanUtils.transBean2Map(merchantOrderForm);
        param.put("table_name", getTableName(merchantOrderForm.getMerchantId()));
        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.update", param) > 0;
    }

    @Override
    public List<MerchantOrderForm> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantOrderForm> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantOrderForm> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchantOrderForm> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.count4page", param);
        Page<MerchantOrderForm> page = new Page<MerchantOrderForm>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("state", query.getState());
        param.put("startDate", query.getStartOrderDate());
        param.put("endDate", query.getEndOrderDate());
        param.put("merchantId", merchantId);
        param.put("table_name", getTableName(merchantId));
        List<MerchantOrderForm> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.count4query", param);
        Page<MerchantOrderForm> page = new Page<MerchantOrderForm>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchantOrderForm> listAll() {

        List<MerchantOrderForm> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.listAll");
        return list;
    }

    private String getTableName(long merchantId) {

        return TableSplitUtil.getTableSplitName(merchantId, "sellercenter_merchant_order_form", 100);
    }

    @Override
    public MerchantOrderForm getBySubOrder(Long merchantId, Long subOrderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(merchantId));
        param.put("merchantId", merchantId);
        param.put("subOrderId", subOrderId);
        param.put("start", 0);
        param.put("count", 1);
        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.getBySubOrder", param);
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, long storeId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("state", query.getState());
        param.put("startDate", query.getStartOrderDate());
        param.put("endDate", query.getEndOrderDate());
        param.put("storeId", storeId);
        param.put("table_name", getTableName(merchantId));
        List<MerchantOrderForm> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.list4query2", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.count4query2", param);
        Page<MerchantOrderForm> page = new Page<MerchantOrderForm>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchantOrderForm> reportForm4merchant(Long merchantId, String startDate, String endDate) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("merchantId", merchantId);
        param.put("table_name", getTableName(merchantId));
        List<MerchantOrderForm> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.reportForm4merchant", param);
        return list;
    }

    @Override
    public List<MerchantOrderForm> reportForm4store(Long merchantId, Long storeId, String startDate, String endDate) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("storeId", storeId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("merchantId", merchantId);
        param.put("table_name", getTableName(merchantId));
        List<MerchantOrderForm> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.reportForm4store", param);
        return list;
    }

    @Override
    public MerchantOrderForm get(long orderId, long merchantId, long storeId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", 0);
        param.put("count", 1);
        param.put("storeId", storeId);
        param.put("orderId", orderId);
        param.put("merchantId", merchantId);
        param.put("table_name", getTableName(merchantId));
        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.getByOrderId", param);
    }

    @Override
    public List<MerchantOrderForm> list4Store(Long merchantId, Long storeId, int state, int start, int count) {
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantId", merchantId);
        param.put("storeId", storeId);
        param.put("state", state);
        param.put("start", start);
        param.put("count", count);
        param.put("table_name", getTableName(merchantId));
        List<MerchantOrderForm> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantOrderFormMapper.list4Store", param);
        return list;  
    }
}
