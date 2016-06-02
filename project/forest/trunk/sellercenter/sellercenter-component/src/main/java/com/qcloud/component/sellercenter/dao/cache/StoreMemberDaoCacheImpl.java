package com.qcloud.component.sellercenter.dao.cache;

import com.qcloud.component.sellercenter.dao.StoreMemberDao;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class StoreMemberDaoCacheImpl implements StoreMemberDao {

    @Autowired
    private StoreMemberDao storeMemberDaoMysqlImpl;

//	@Autowired
//	private StoreMemberDao storeMemberDaoRedisImpl;

    @Override
    public boolean add(StoreMember storeMember) {
        return storeMemberDaoMysqlImpl.add(storeMember);
    }

    @Override
    public StoreMember get(Long id) {
        return storeMemberDaoMysqlImpl.get(id);
//		return CacheLoader.get(storeMemberDaoRedisImpl, storeMemberDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {
        return storeMemberDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(StoreMember storeMember) {
        return storeMemberDaoMysqlImpl.update(storeMember);
    }

    @Override
    public List<StoreMember> list(List<Long> idList) {
        return CacheLoader.list(storeMemberDaoMysqlImpl, idList);
//		return CacheLoader.list(storeMemberDaoRedisImpl, storeMemberDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, StoreMember> map(Set<Long> idSet) {
        return CacheLoader.map(storeMemberDaoMysqlImpl, idSet);
//		return CacheLoader.map(storeMemberDaoRedisImpl, storeMemberDaoMysqlImpl, idSet);
    }

    @Override
    public Page<StoreMember> page(int start, int count) {
        return storeMemberDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<StoreMember> page(StoreMemberQuery query, int start, int count) {
        return storeMemberDaoMysqlImpl.page(query, start, count);
    }

    @Override
    public List<StoreMember> listAll() {
        return storeMemberDaoMysqlImpl.listAll();
    }

    @Override
    public StoreMember get(HashMap where) {
        return storeMemberDaoMysqlImpl.get(where);
    }

    @Override
    public List<StoreMember> list(HashMap where) {
        return storeMemberDaoMysqlImpl.list(where);
    }

    @Override
    public Page<StoreMember> page(HashMap where, int start, int size) {
        return storeMemberDaoMysqlImpl.page(where, start, size);
    }
}

