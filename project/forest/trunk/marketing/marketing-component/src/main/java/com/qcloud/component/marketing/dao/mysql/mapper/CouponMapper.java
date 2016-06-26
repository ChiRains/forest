package com.qcloud.component.marketing.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.query.CouponQuery;

public interface CouponMapper {

	@Insert("insert into `marketing_coupon`(`id`,`image`,`interval`,`startDate`,`endDate`,`validDate`,`totalOfPerson`,`priceOfPerson`,`description`,`enable`,`merchantId`,`type`,`invalidDate`)"
			+ " values(#{id},#{image},#{interval},#{startDate},#{endDate},#{validDate},#{totalOfPerson},#{priceOfPerson},#{description},#{enable},#{merchantId},#{type},#{invalidDate})")
	public void insert(Coupon coupon);

	@Select("select * from `marketing_coupon` where `id`=#{id}")
	public Coupon get(Long id);

	@Update("update `marketing_coupon` set `image`=#{image}, `interval`=#{interval}, `startDate`=#{startDate},`endDate`=#{endDate},`validDate`=#{validDate},`totalOfPerson`=#{totalOfPerson},`priceOfPerson`=#{priceOfPerson},`description`=#{description},`enable`=#{enable},`merchantId`=#{merchantId},`type`=#{type} ,`invalidDate`=#{invalidDate}  where `id`=#{id}")
	public void update(Coupon coupon);

	@Delete("delete from `marketing_coupon` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `marketing_coupon` limit #{start},#{count}")
	public List<Coupon> list4page(Map<String,Object> param);

	@Select("select count(*) from `marketing_coupon`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `marketing_coupon`")
	public List<Coupon> listAll();
}