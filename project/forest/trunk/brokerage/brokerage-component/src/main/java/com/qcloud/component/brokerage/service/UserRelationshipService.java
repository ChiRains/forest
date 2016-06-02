package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.key.TypeEnum.UserAllocationType;
import com.qcloud.component.brokerage.model.query.UserRelationshipQuery;
import com.qcloud.pirates.data.Page;

public interface UserRelationshipService {

    public boolean add(UserRelationship userRelationship);

    public UserRelationship get(Long id);

    public UserRelationship getByUserId(Long userId);

    public boolean delete(Long id);

    public boolean update(UserRelationship userRelationship);

    public Page<UserRelationship> page(UserRelationshipQuery query, int start, int count);

    public Page<UserRelationship> pageRecommed(UserRelationshipQuery query, int start, int count);

    public List<UserRelationship> listAll();

    List<UserRelationship> listChildren(long recommedId, UserAllocationType type);

    List<UserRelationship> listChildren(long recommedId, UserAllocationType type, int level);

    int countChildren(long recommedId, UserAllocationType type);

    public Page<UserRelationship> pageOneChildren(long recommedId, int start, int count);

    public Page<UserRelationship> pageTwoChildren(long recommedId, int start, int count);

    public Page<UserRelationship> pageThreeChildren(long recommedId, int start, int count);

    public int getCountByOneChildren(long recommedId);

    public int getCountByTwoChildren(long recommedId);

    public int getCountByThreeChildren(long recommedId);
}
