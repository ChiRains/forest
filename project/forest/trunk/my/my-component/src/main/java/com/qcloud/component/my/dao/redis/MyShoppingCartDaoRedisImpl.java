package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyShoppingCartDao;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.query.MyShoppingCartQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyShoppingCartDaoRedisImpl implements MyShoppingCartDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyShoppingCart myShoppingCart) {

        throw new NotImplementedException();
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

        throw new NotImplementedException();
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

        throw new NotImplementedException();
    }

    @Override
    public MyShoppingCart get(Long id, Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public MyShoppingCart getByUnifiedMerchandise(Long unifiedMerchandiseId, Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id, Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public int count(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean clean(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyShoppingCart> listByGroup(String group, Long userId) {

        throw new NotImplementedException();
    }
}
