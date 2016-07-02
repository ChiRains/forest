package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.model.query.MyCollectionQuery;
import com.qcloud.pirates.data.Page;

public interface MyCollectionService {

    public boolean add(MyCollection myCollection);

    public MyCollection get(Long id);

    public MyCollection get(Long id, Long userId);

    public MyCollection getByObject(Long objId, Long userId, CollectionType type);

    public boolean delete(Long id);

    public boolean delete(Long id, Long userId);

    public boolean update(MyCollection myCollection);

    public Page<MyCollection> page(MyCollectionQuery query, int start, int count);

    public List<MyCollection> listAll();

    public List<MyCollection> list(Long userId, CollectionType type, Long classifyId, int start, int count);

    public int count(Long objId, CollectionType type);

    public int countByUserId(Long userId);
}
