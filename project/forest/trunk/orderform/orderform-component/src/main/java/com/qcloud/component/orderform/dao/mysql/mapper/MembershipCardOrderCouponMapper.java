package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.model.query.MembershipCardOrderCouponQuery;

public interface MembershipCardOrderCouponMapper {

	@Insert("insert into `orderform_membership_card_order_coupon`(`id`,`orderId`,`myCouponId`,`coupon`)"
			+ " values(#{id},#{orderId},#{myCouponId},#{coupon})")
	public void insert(MembershipCardOrderCoupon membershipCardOrderCoupon);

	@Select("select * from `orderform_membership_card_order_coupon` where `id`=#{id}")
	public MembershipCardOrderCoupon get(Long id);

	@Update("update `orderform_membership_card_order_coupon` set `orderId`=#{orderId},`myCouponId`=#{myCouponId},`coupon`=#{coupon} where `id`=#{id}")
	public void update(MembershipCardOrderCoupon membershipCardOrderCoupon);

	@Delete("delete from `orderform_membership_card_order_coupon` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_membership_card_order_coupon` limit #{start},#{count}")
	public List<MembershipCardOrderCoupon> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_membership_card_order_coupon`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_membership_card_order_coupon`")
	public List<MembershipCardOrderCoupon> listAll();
}