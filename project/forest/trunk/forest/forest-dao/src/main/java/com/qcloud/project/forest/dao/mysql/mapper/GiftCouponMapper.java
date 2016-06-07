package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.query.GiftCouponQuery;

public interface GiftCouponMapper {

	@Insert("insert into `forest_gift_coupon`(`id`,`image`,`name`,`validDate`,`inValidDate`,`enable`,`desc`)"
			+ " values(#{id},#{image},#{name},#{validDate},#{inValidDate},#{enable},#{desc})")
	public void insert(GiftCoupon giftCoupon);

	@Select("select * from `forest_gift_coupon` where `id`=#{id}")
	public GiftCoupon get(Long id);

	@Update("update `forest_gift_coupon` set `image`=#{image},`name`=#{name},`validDate`=#{validDate},`inValidDate`=#{inValidDate},`enable`=#{enable},`desc`=#{desc} where `id`=#{id}")
	public void update(GiftCoupon giftCoupon);

	@Delete("delete from `forest_gift_coupon` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_gift_coupon` limit #{start},#{count}")
	public List<GiftCoupon> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_gift_coupon`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_gift_coupon`")
	public List<GiftCoupon> listAll();
}