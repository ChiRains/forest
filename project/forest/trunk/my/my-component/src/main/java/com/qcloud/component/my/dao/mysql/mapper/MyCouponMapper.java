package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;

public interface MyCouponMapper {

	@Insert("insert into `my_my_coupon`(`id`,`userId`,`couponId`,`couponItemId`,`extractDate`,`validDate`,`name`,`price`,`limitPrice`,`state`,`orderId`,`orderDate`,`merchantId`,`code`)"
			+ " values(#{id},#{userId},#{couponId},#{couponItemId},#{extractDate},#{validDate},#{name},#{price},#{limitPrice},#{state},#{orderId},#{orderDate},#{merchantId},#{code})")
	public void insert(MyCoupon myCoupon);

	@Select("select * from `my_my_coupon` where `id`=#{id}")
	public MyCoupon get(Long id);

	@Update("update `my_my_coupon` set `userId`=#{userId},`couponId`=#{couponId},`couponItemId`=#{couponItemId},`extractDate`=#{extractDate},`validDate`=#{validDate},`name`=#{name},`price`=#{price},`limitPrice`=#{limitPrice},`state`=#{state},`orderId`=#{orderId},`orderDate`=#{orderDate},`merchantId`=#{merchantId},`code`=#{code} where `id`=#{id}")
	public void update(MyCoupon myCoupon);

	@Delete("delete from `my_my_coupon` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_my_coupon` limit #{start},#{count}")
	public List<MyCoupon> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_my_coupon`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_my_coupon`")
	public List<MyCoupon> listAll();
}