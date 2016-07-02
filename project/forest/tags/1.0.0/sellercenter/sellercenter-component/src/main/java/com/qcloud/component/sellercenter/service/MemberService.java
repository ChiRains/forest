package com.qcloud.component.sellercenter.service;

import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.model.query.MemberQuery;
import com.qcloud.pirates.data.Page;

import java.util.List;

public interface MemberService {

    public boolean add(Member member, Long merchantId);

    public Member get(Long id);

    public boolean delete(Long id);

    public boolean update(Member member);

    public Page<Member> page(MemberQuery query, int start, int count);

    public List<Member> listAll();

    Member getByAccount(String account);

    String getMemberPermissionAccountCode(Member member);

    String getMemberPermissionAccountCode(String account);

    boolean isMember(String account, String password);

    String getEncodePsw(String psw);

    String getEncodeDefaultPwd();
}
