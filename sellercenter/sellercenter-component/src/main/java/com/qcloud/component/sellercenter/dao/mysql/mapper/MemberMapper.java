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
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.query.MemberQuery;
//
//public interface MemberMapper {
//
//	@Insert("insert into `sellercenter_member`(`id`,`account`,`name`,`sex`,`nickname`,`password`,`mobile`,`qq`,`headImage`,`enable`,`userId`)"
//			+ " values(#{id},#{account},#{name},#{sex},#{nickname},#{password},#{mobile},#{qq},#{headImage},#{enable},#{userId})")
//	public void insert(Member member);
//
//	@Select("select * from `sellercenter_member` where `id`=#{id}")
//	public Member get(Long id);
//
//	@Update("update `sellercenter_member` set `account`=#{account},`name`=#{name},`sex`=#{sex},`nickname`=#{nickname},`password`=#{password},`mobile`=#{mobile},`qq`=#{qq},`headImage`=#{headImage},`enable`=#{enable},`userId`=#{userId} where `id`=#{id}")
//	public void update(Member member);
//
//	@Delete("delete from `sellercenter_member` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `sellercenter_member` limit #{start},#{count}")
//	public List<Member> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `sellercenter_member`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `sellercenter_member`")
//	public List<Member> listAll();
//}