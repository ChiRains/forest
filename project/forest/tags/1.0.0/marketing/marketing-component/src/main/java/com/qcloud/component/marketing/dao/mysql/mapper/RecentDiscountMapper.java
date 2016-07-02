package com.qcloud.component.marketing.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;

public interface RecentDiscountMapper {

	@Insert("insert into `marketing_recent_discount`(`id`,`merchantId`,`name`,`image`,`startDate`,`endDate`,`enable`)"
			+ " values(#{id},#{merchantId},#{name},#{image},#{startDate},#{endDate},#{enable})")
	public void insert(RecentDiscount recentDiscount);

	@Select("select * from `marketing_recent_discount` where `id`=#{id}")
	public RecentDiscount get(Long id);

	@Update("update `marketing_recent_discount` set `merchantId`=#{merchantId},`name`=#{name},`image`=#{image},`startDate`=#{startDate},`endDate`=#{endDate},`enable`=#{enable} where `id`=#{id}")
	public void update(RecentDiscount recentDiscount);

	@Delete("delete from `marketing_recent_discount` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `marketing_recent_discount` limit #{start},#{count}")
	public List<RecentDiscount> list4page(Map<String,Object> param);

	@Select("select count(*) from `marketing_recent_discount`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `marketing_recent_discount`")
	public List<RecentDiscount> listAll();
}