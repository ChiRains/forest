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
//import com.qcloud.component.sellercenter.model.StoreMember;
//import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
//
//public interface StoreMemberMapper {
//
//	@Insert("insert into `sellercenter_store_member`(`id`,`memberId`,`merchantId`,`storeId`)"
//			+ " values(#{id},#{memberId},#{merchantId},#{storeId})")
//	public void insert(StoreMember storeMember);
//
//	@Select("select * from `sellercenter_store_member` where `id`=#{id}")
//	public StoreMember get(Long id);
//
//	@Update("update `sellercenter_store_member` set `memberId`=#{memberId},`merchantId`=#{merchantId},`storeId`=#{storeId} where `id`=#{id}")
//	public void update(StoreMember storeMember);
//
//	@Delete("delete from `sellercenter_store_member` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `sellercenter_store_member` limit #{start},#{count}")
//	public List<StoreMember> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `sellercenter_store_member`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `sellercenter_store_member`")
//	public List<StoreMember> listAll();
//}