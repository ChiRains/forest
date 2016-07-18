package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.model.query.MyCollectionQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyCollectionDao extends ISimpleDao<MyCollection, Long> {

    public boolean add(MyCollection myCollection);

    public MyCollection get(Long id);

    MyCollection get(Long id, Long userId);

    MyCollection getByObject(Long objId, Long userId, CollectionType type);

    public boolean delete(Long id);

    boolean delete(Long id, Long userId);

    public boolean update(MyCollection myCollection);

    public List<MyCollection> list(List<Long> idList);

    public Map<Long, MyCollection> map(Set<Long> idSet);

    public Page<MyCollection> page(MyCollectionQuery query, int start, int size);

    public List<MyCollection> listAll();

    List<MyCollection> list(Long userId, CollectionType type, Long classifyId, int start, int count);

    public int countByUserId(Long userId);

    public List<MyCollection> listByUser(Long userId, CollectionType type, Long classifyId);
}
