package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantSortDao;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.model.query.MerchantSortQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.StringUtil;

@Repository
public class MerchantSortDaoMysqlImpl implements MerchantSortDao {

    @Resource(name="sqlOperator-sellercenter")
    private SqlOperator sqlOperator;
    

    @Override
    public boolean add(MerchantSort merchantSort) {
        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.insert",merchantSort)==1;
    }

    @Override
    public MerchantSort get(Long id) {
        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.get",id);
    }

    @Override
    public boolean delete(Long id) {
        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.delete",id)>0;
    }

    @Override
    public boolean update(MerchantSort merchantSort) {
        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.update",merchantSort)>0;
    }

    @Override
    public List<MerchantSort> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantSort> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantSort> page(MerchantSortQuery query, int start, int count) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("name", StringUtil.nullToEmpty(query.getName()));
        
        List<MerchantSort> list=sqlOperator.selectList(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.list4query",
                param); 
        int total=sqlOperator.selectOne(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.count4query",
                param);
        Page<MerchantSort> page=new Page<MerchantSort>();
        page.setCount(total);
        page.setData(list);
        return page;
    }
    
    @Override
    public Page<MerchantSort> page(int start, int count) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        
        List<MerchantSort> list=sqlOperator.selectList(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.list4page",
                param); 
        int total=sqlOperator.selectOne(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.count4page",
                param);
        Page<MerchantSort> page=new Page<MerchantSort>();
        page.setCount(total);
        page.setData(list);
        return page;
    }


    @Override
    public List<MerchantSort> listAll() {
        List<MerchantSort> list=sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.listAll");
        return list;
    }

    @Override
    public List<MerchantSort> list(int start, int count) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("start", start);
        map.put("count", count);
        List<MerchantSort> list=sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantSortMapper.list",map);
        return list;
    }

}
