package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;

public interface GiftCouponUserMapper {

	@Insert("insert into `forest_gift_coupon_user`(`id`,`userId`,`giftCouponId`,`extractDate`,`validDate`,`inValidDate`,`name`,`orderId`,`orderDate`,`state`,`image`)"
			+ " values(#{id},#{userId},#{giftCouponId},#{extractDate},#{validDate},#{inValidDate},#{name},#{orderId},#{orderDate},#{state},#{image})")
	public void insert(GiftCouponUser giftCouponUser);

	@Select("select * from `forest_gift_coupon_user` where `id`=#{id}")
	public GiftCouponUser get(Long id);

	@Update("update `forest_gift_coupon_user` set `userId`=#{userId},`giftCouponId`=#{giftCouponId},`extractDate`=#{extractDate},`validDate`=#{validDate},`inValidDate`=#{inValidDate},`name`=#{name},`orderId`=#{orderId},`orderDate`=#{orderDate},`state`=#{state},`image`=#{image} where `id`=#{id}")
	public void update(GiftCouponUser giftCouponUser);

	@Delete("delete from `forest_gift_coupon_user` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_gift_coupon_user` limit #{start},#{count}")
	public List<GiftCouponUser> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_gift_coupon_user`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_gift_coupon_user`")
	public List<GiftCouponUser> listAll();
}