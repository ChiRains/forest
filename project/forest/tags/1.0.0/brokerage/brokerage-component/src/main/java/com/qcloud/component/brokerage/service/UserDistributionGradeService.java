package com.qcloud.component.brokerage.service;

import java.util.List;
import java.util.Map;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;

public interface UserDistributionGradeService {

    public boolean add(UserDistributionGrade userDistributionGrade);

    public UserDistributionGrade get(Long id);

    public boolean delete(Long id);

    public boolean update(UserDistributionGrade userDistributionGrade);

    boolean changeUserGrade(long userId, long gradeId);

    public Page<UserDistributionGrade> page(UserDistributionGradeQuery query, int start, int count);

    public List<UserDistributionGrade> listAll();

    public UserDistributionGrade getByUser(long userId);

    Map<String, Object> requestAlipayPay(long userId, long gradeId);

    boolean checkIsValidAttach(String attach);

    public UserDistributionGrade getByUserForAdmin(long userId);
}
