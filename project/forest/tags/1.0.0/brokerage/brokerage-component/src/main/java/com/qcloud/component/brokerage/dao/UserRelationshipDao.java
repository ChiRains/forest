package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.key.TypeEnum.UserAllocationType;
import com.qcloud.component.brokerage.model.query.UserRelationshipQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface UserRelationshipDao extends ISimpleDao<UserRelationship, Long> {

    public boolean add(UserRelationship userRelationship);

    public UserRelationship get(Long id);

    public boolean delete(Long id);

    public boolean update(UserRelationship userRelationship);

    public List<UserRelationship> list(List<Long> idList);

    public Map<Long, UserRelationship> map(Set<Long> idSet);

    public Page<UserRelationship> page(UserRelationshipQuery query, int start, int size);

    public List<UserRelationship> listAll();

    UserRelationship getByUserId(Long userId);

    List<UserRelationship> listChildren(long recommedId, UserAllocationType type);

    int countChildren(long recommedId, UserAllocationType allocation);

    Page<UserRelationship> pageRecommed(UserRelationshipQuery query, int start, int count);

    public Page<UserRelationship> pageOneChildren(long recommedId, int start, int count);

    public Page<UserRelationship> pageTwoChildren(long recommedId, int start, int count);

    public Page<UserRelationship> pageThreeChildren(long recommedId, int start, int count);

    public int getCountByOneChildren(long recommedId);

    public int getCountByTwoChildren(long recommedId);

    public int getCountByThreeChildren(long recommedId);
}
