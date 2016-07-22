package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.query.MyShoppingCartQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyShoppingCartDao extends ISimpleDao<MyShoppingCart, Long> {

    public boolean add(MyShoppingCart myShoppingCart);

    public MyShoppingCart get(Long id);

    public boolean delete(Long id);

    boolean delete(Long id, Long userId);

    public boolean update(MyShoppingCart myShoppingCart);

    public List<MyShoppingCart> list(List<Long> idList);

    public Map<Long, MyShoppingCart> map(Set<Long> idSet);

    public Page<MyShoppingCart> page(MyShoppingCartQuery query, int start, int size);

    public List<MyShoppingCart> listAll();

    List<MyShoppingCart> list(Long userId, int start, int count);

    MyShoppingCart get(Long id, Long userId);

    MyShoppingCart getByUnifiedMerchandise(Long unifiedMerchandiseId, Long userId);

    int count(Long userId);

    boolean clean(Long userId);

    public List<MyShoppingCart> listByGroup(String group, Long userId);
}
