//package com.qcloud.component.sellercenter.service;
//
//import java.util.List;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.MerchantMember;
//import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;
//import com.qcloud.pirates.data.Page;
//
//public interface MerchantMemberService {
//
//    public boolean add(Member member, Long merchantId);
//
//    public boolean add(MerchantMember merchantMember);
//
//    public MerchantMember get(Long id);
//
//    public MerchantMember get(Long memberId, Long merchantId);
//
//    public boolean delete(Long id);
//
//    public boolean update(MerchantMember merchantMember);
//
//    public Page<MerchantMember> page(MerchantMemberQuery query, int start, int count);
//
//    public List<MerchantMember> listAll();
//
//    List<MerchantMember> listByMerchant(Long merchantId);
//
//    List<MerchantMember> listByMember(Long memberId);
//    
//}
