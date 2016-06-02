package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.model.query.MembershipCardOrderQuery;

public interface MembershipCardOrderMapper {

	@Insert("insert into `orderform_membership_card_order`(`id`,`orderNumber`,`userId`,`merchantId`,`storeId`,`time`,`sum`,`cash`)"
			+ " values(#{id},#{orderNumber},#{userId},#{merchantId},#{storeId},#{time},#{sum},#{cash})")
	public void insert(MembershipCardOrder membershipCardOrder);

	@Select("select * from `orderform_membership_card_order` where `id`=#{id}")
	public MembershipCardOrder get(Long id);

	@Update("update `orderform_membership_card_order` set `orderNumber`=#{orderNumber},`userId`=#{userId},`merchantId`=#{merchantId},`storeId`=#{storeId},`time`=#{time},`sum`=#{sum},`cash`=#{cash} where `id`=#{id}")
	public void update(MembershipCardOrder membershipCardOrder);

	@Delete("delete from `orderform_membership_card_order` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_membership_card_order` limit #{start},#{count}")
	public List<MembershipCardOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_membership_card_order`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_membership_card_order`")
	public List<MembershipCardOrder> listAll();
}