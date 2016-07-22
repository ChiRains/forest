package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyShoppingCartDao;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.query.MyShoppingCartQuery;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.TableSplitUtil;

@Repository
public class MyShoppingCartDaoMysqlImpl implements MyShoppingCartDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyShoppingCart myShoppingCart) {

        Map<String, Object> map = BeanUtils.transBean2Map(myShoppingCart);
        map.put("table_name", getTableName(myShoppingCart.getUserId()));
        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.insert", map) == 1;
    }

    @Override
    public MyShoppingCart get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyShoppingCart myShoppingCart) {

        Map<String, Object> map = BeanUtils.transBean2Map(myShoppingCart);
        map.put("table_name", getTableName(myShoppingCart.getUserId()));
        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.update", map) > 0;
    }

    @Override
    public List<MyShoppingCart> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyShoppingCart> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyShoppingCart> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyShoppingCart> page(MyShoppingCartQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyShoppingCart> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MyShoppingCart> list(Long userId, int start, int count) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        map.put("start", start);
        map.put("count", count);
        List<MyShoppingCart> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.list", map);
        return list;
    }

    @Override
    public MyShoppingCart get(Long id, Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        map.put("id", id);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.get", map);
    }

    @Override
    public MyShoppingCart getByUnifiedMerchandise(Long unifiedMerchandiseId, Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        map.put("unifiedMerchandiseId", unifiedMerchandiseId);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.getByUnifiedMerchandise", map);
    }

    @Override
    public boolean delete(Long id, Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        map.put("id", id);
        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.delete", map) > 0;
    }

    private String getTableName(long userId) {

        return TableSplitUtil.getTableSplitName(userId, "my_my_shopping_cart", 100);
    }

    @Override
    public int count(Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        Integer total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.count", map);
        if (total == null) {
            return 0;
        }
        return total;
    }

    @Override
    public boolean clean(Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.clean", map) > 0;
    }

    @Override
    public List<MyShoppingCart> listByGroup(String group, Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table_name", getTableName(userId));
        map.put("userId", userId);
        map.put("group", group);
        return sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyShoppingCartMapper.listByGroup", map);
    }
}
