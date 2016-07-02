package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.query.MerchandiseMarketingQuery;

public interface MerchandiseMarketingMapper {

	@Insert("insert into `commoditycenter_merchandise_marketing`(`id`,`enable`,`merchandiseItemId`,`unifiedMerchandiseId`,`purchase`,`discount`,`price`,`stock`,`sence`,`updateTime`,`order`,`currencyType`,`activityId`,`name`,`sysCode`)"
			+ " values(#{id},#{enable},#{merchandiseItemId},#{unifiedMerchandiseId},#{purchase},#{discount},#{price},#{stock},#{sence},#{updateTime},#{order},#{currencyType},#{activityId},#{name},#{sysCode})")
	public void insert(MerchandiseMarketing merchandiseMarketing);

	@Select("select * from `commoditycenter_merchandise_marketing` where `id`=#{id}")
	public MerchandiseMarketing get(Long id);

	@Update("update `commoditycenter_merchandise_marketing` set `enable`=#{enable},`merchandiseItemId`=#{merchandiseItemId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`purchase`=#{purchase},`discount`=#{discount},`price`=#{price},`stock`=#{stock},`sence`=#{sence},`updateTime`=#{updateTime},`order`=#{order} ,`currencyType`=#{currencyType} ,`activityId`=#{activityId},`name`=#{name},`sysCode`=#{sysCode} where `id`=#{id}")
	public void update(MerchandiseMarketing merchandiseMarketing);

	@Delete("delete from `commoditycenter_merchandise_marketing` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_merchandise_marketing` limit #{start},#{count}")
	public List<MerchandiseMarketing> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_merchandise_marketing`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_merchandise_marketing`")
	public List<MerchandiseMarketing> listAll();
}