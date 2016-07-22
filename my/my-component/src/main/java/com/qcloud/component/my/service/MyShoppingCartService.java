package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.query.MyShoppingCartQuery;
import com.qcloud.pirates.data.Page;

public interface MyShoppingCartService {

    public boolean add(MyShoppingCart myShoppingCart);

    public MyShoppingCart get(Long id);

    public MyShoppingCart get(Long id, Long userId);

    public MyShoppingCart getByUnifiedMerchandise(Long unifiedMerchandiseId, Long userId);

    public boolean delete(Long id);

    public boolean delete(Long id, Long userId);

    public boolean clean(Long userId);

    public boolean update(MyShoppingCart myShoppingCart);

    public Page<MyShoppingCart> page(MyShoppingCartQuery query, int start, int count);

    public List<MyShoppingCart> listAll();

    public List<MyShoppingCart> list(Long userId, int start, int count);

    public int count(Long userId);

    public List<MyShoppingCart> listByGroup(String group, Long userId);
}
