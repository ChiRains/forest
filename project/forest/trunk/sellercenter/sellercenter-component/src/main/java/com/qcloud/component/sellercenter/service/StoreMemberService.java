package com.qcloud.component.sellercenter.service;

import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
import com.qcloud.pirates.data.Page;

import java.util.HashMap;
import java.util.List;

public interface StoreMemberService {

    public boolean add(StoreMember storeMember);

    public boolean add(Member member, Long merchantId, Long storeId);

    public StoreMember get(Long id);

    public boolean delete(Long id);

    public boolean update(StoreMember storeMember);

    public Page<StoreMember> page(StoreMemberQuery query, int start, int count);

    public List<StoreMember> listAll();

    public StoreMember get(HashMap where);

    public List<StoreMember> list(HashMap where);

    public Page<StoreMember> page(HashMap where, int start, int size);
}
