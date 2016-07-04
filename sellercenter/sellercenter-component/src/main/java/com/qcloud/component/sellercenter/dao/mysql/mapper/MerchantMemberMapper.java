//package com.qcloud.component.sellercenter.dao.mysql.mapper;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import com.qcloud.component.sellercenter.model.MerchantMember;
//import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;
//
//public interface MerchantMemberMapper {
//
//	@Insert("insert into `sellercenter_merchant_member`(`id`,`memberId`,`merchantId`)"
//			+ " values(#{id},#{memberId},#{merchantId})")
//	public void insert(MerchantMember merchantMember);
//
//	@Select("select * from `sellercenter_merchant_member` where `id`=#{id}")
//	public MerchantMember get(Long id);
//
//	@Update("update `sellercenter_merchant_member` set `memberId`=#{memberId},`merchantId`=#{merchantId} where `id`=#{id}")
//	public void update(MerchantMember merchantMember);
//
//	@Delete("delete from `sellercenter_merchant_member` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `sellercenter_merchant_member` limit #{start},#{count}")
//	public List<MerchantMember> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `sellercenter_merchant_member`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `sellercenter_merchant_member`")
//	public List<MerchantMember> listAll();
//}