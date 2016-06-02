package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;

public interface UserDistributionGradeDao extends ISimpleDao<UserDistributionGrade, Long> {

    public boolean add(UserDistributionGrade userDistributionGrade);

    public UserDistributionGrade get(Long id);

    public boolean delete(Long id);

    public boolean update(UserDistributionGrade userDistributionGrade);

    public List<UserDistributionGrade> list(List<Long> idList);

    public Map<Long, UserDistributionGrade> map(Set<Long> idSet);

    public Page<UserDistributionGrade> page(UserDistributionGradeQuery query, int start, int size);

    public List<UserDistributionGrade> listAll();

    UserDistributionGrade getByUser(long userId);
}
