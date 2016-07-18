package com.qcloud.component.marketing.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;

public interface CouponItemsMapper {

	@Insert("insert into `marketing_coupon_items`(`id`,`couponID`,`name`,`price`,`totalNum`,`sendNum`,`usedNum`,`limitPrice`,`type`,`cash`)"
			+ " values(#{id},#{couponID},#{name},#{price},#{totalNum},#{sendNum},#{usedNum},#{limitPrice},#{type},#{cash})")
	public void insert(CouponItems couponItems);

	@Select("select * from `marketing_coupon_items` where `id`=#{id}")
	public CouponItems get(Long id);

	@Update("update `marketing_coupon_items` set `couponID`=#{couponID},`name`=#{name},`price`=#{price},`totalNum`=#{totalNum},`sendNum`=#{sendNum},`usedNum`=#{usedNum},`limitPrice`=#{limitPrice},`type`=#{type},`cash`=#{cash}  where `id`=#{id}")
	public void update(CouponItems couponItems);

	@Delete("delete from `marketing_coupon_items` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `marketing_coupon_items` limit #{start},#{count}")
	public List<CouponItems> list4page(Map<String,Object> param);

	@Select("select count(*) from `marketing_coupon_items`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `marketing_coupon_items`")
	public List<CouponItems> listAll();
	
}