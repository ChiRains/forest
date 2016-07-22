package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyShoppingCartDao;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.query.MyShoppingCartQuery;
import com.qcloud.component.my.service.MyShoppingCartService;
import com.qcloud.pirates.data.Page;

@Service
public class MyShoppingCartServiceImpl implements MyShoppingCartService {

    @Autowired
    private MyShoppingCartDao   myShoppingCartDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_my_shopping_cart";

    @Override
    public boolean add(MyShoppingCart myShoppingCart) {

        long id = autoIdGenerator.get(ID_KEY);
        myShoppingCart.setId(id);
        return myShoppingCartDao.add(myShoppingCart);
    }

    @Override
    public MyShoppingCart get(Long id) {

        return myShoppingCartDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myShoppingCartDao.delete(id);
    }

    @Override
    public boolean update(MyShoppingCart myShoppingCart) {

        return myShoppingCartDao.update(myShoppingCart);
    }

    @Override
    public Page<MyShoppingCart> page(MyShoppingCartQuery query, int start, int count) {

        return myShoppingCartDao.page(query, start, count);
    }

    public List<MyShoppingCart> listAll() {

        return myShoppingCartDao.listAll();
    }

    @Override
    public List<MyShoppingCart> list(Long userId, int start, int count) {

        return myShoppingCartDao.list(userId, start, count);
    }

    @Override
    public MyShoppingCart get(Long id, Long userId) {

        return myShoppingCartDao.get(id, userId);
    }

    @Override
    public MyShoppingCart getByUnifiedMerchandise(Long unifiedMerchandiseId, Long userId) {

        return myShoppingCartDao.getByUnifiedMerchandise(unifiedMerchandiseId, userId);
    }

    @Override
    public boolean delete(Long id, Long userId) {

        return myShoppingCartDao.delete(id, userId);
    }

    @Override
    public int count(Long userId) {

        return myShoppingCartDao.count(userId);
    }

    @Override
    public boolean clean(Long userId) {

        return myShoppingCartDao.clean(userId);
    }

    @Override
    public List<MyShoppingCart> listByGroup(String group, Long userId) {

        return myShoppingCartDao.listByGroup(group, userId);
    }
}
