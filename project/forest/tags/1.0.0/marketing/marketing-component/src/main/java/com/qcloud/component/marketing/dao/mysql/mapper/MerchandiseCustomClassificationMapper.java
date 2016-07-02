package com.qcloud.component.marketing.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;

public interface MerchandiseCustomClassificationMapper {

	@Insert("insert into `marketing_merchandise_custom_classification`(`id`,`merchantId`,`unifiedMerchandiseId`,`orderNum`,`customClassifyId`,`name`,`sysCode`)"
			+ " values(#{id},#{merchantId},#{unifiedMerchandiseId},#{orderNum},#{customClassifyId},#{name},#{sysCode})")
	public void insert(MerchandiseCustomClassification merchandiseCustomClassification);

	@Select("select * from `marketing_merchandise_custom_classification` where `id`=#{id}")
	public MerchandiseCustomClassification get(Long id);

	@Update("update `marketing_merchandise_custom_classification` set `merchantId`=#{merchantId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`orderNum`=#{orderNum},`customClassifyId`=#{customClassifyId},`name`=#{name},`sysCode`=#{sysCode} where `id`=#{id}")
	public void update(MerchandiseCustomClassification merchandiseCustomClassification);

	@Delete("delete from `marketing_merchandise_custom_classification` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `marketing_merchandise_custom_classification` limit #{start},#{count}")
	public List<MerchandiseCustomClassification> list4page(Map<String,Object> param);

	@Select("select count(*) from `marketing_merchandise_custom_classification`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `marketing_merchandise_custom_classification`")
	public List<MerchandiseCustomClassification> listAll();
}