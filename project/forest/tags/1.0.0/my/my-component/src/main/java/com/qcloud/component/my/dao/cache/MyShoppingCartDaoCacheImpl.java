package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyShoppingCartDao;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.query.MyShoppingCartQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyShoppingCartDaoCacheImpl implements MyShoppingCartDao {

    @Autowired
    private MyShoppingCartDao myShoppingCartDaoMysqlImpl;

    // @Autowired
    // private MyShoppingCartDao myShoppingCartDaoRedisImpl;
    @Override
    public boolean add(MyShoppingCart myShoppingCart) {

        return myShoppingCartDaoMysqlImpl.add(myShoppingCart);
    }

    @Override
    public MyShoppingCart get(Long id) {

        return myShoppingCartDaoMysqlImpl.get(id);
        // return CacheLoader.get(myShoppingCartDaoRedisImpl, myShoppingCartDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myShoppingCartDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyShoppingCart myShoppingCart) {

        return myShoppingCartDaoMysqlImpl.update(myShoppingCart);
    }

    @Override
    public List<MyShoppingCart> list(List<Long> idList) {

        return CacheLoader.list(myShoppingCartDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyShoppingCart> map(Set<Long> idSet) {

        return CacheLoader.map(myShoppingCartDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyShoppingCart> page(int start, int count) {

        return myShoppingCartDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyShoppingCart> page(MyShoppingCartQuery query, int start, int count) {

        return myShoppingCartDaoMysqlImpl.page(query, start, count);
    }

    public List<MyShoppingCart> listAll() {

        return myShoppingCartDaoMysqlImpl.listAll();
    }

    @Override
    public List<MyShoppingCart> list(Long userId, int start, int count) {

        return myShoppingCartDaoMysqlImpl.list(userId, start, count);
    }

    @Override
    public MyShoppingCart get(Long id, Long userId) {

        return myShoppingCartDaoMysqlImpl.get(id, userId);
    }

    @Override
    public MyShoppingCart getByUnifiedMerchandise(Long unifiedMerchandiseId, Long userId) {

        return myShoppingCartDaoMysqlImpl.getByUnifiedMerchandise(unifiedMerchandiseId, userId);
    }

    @Override
    public boolean delete(Long id, Long userId) {

        return myShoppingCartDaoMysqlImpl.delete(id, userId);
    }

    @Override
    public int count(Long userId) {

        return myShoppingCartDaoMysqlImpl.count(userId);
    }

    @Override
    public boolean clean(Long userId) {

        return myShoppingCartDaoMysqlImpl.clean(userId);
    }
}
