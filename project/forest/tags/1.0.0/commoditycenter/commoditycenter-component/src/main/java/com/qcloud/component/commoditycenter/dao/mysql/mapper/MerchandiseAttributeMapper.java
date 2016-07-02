package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.MerchandiseAttribute;
import com.qcloud.component.commoditycenter.model.query.MerchandiseAttributeQuery;

public interface MerchandiseAttributeMapper {

	@Insert("insert into `commoditycenter_merchandise_attribute`(`id`,`attributeId`,`merchandiseId`,`value`)"
			+ " values(#{id},#{attributeId},#{merchandiseId},#{value})")
	public void insert(MerchandiseAttribute merchandiseAttribute);

	@Select("select * from `commoditycenter_merchandise_attribute` where `id`=#{id}")
	public MerchandiseAttribute get(Long id);

	@Update("update `commoditycenter_merchandise_attribute` set `attributeId`=#{attributeId},`merchandiseId`=#{merchandiseId},`value`=#{value} where `id`=#{id}")
	public void update(MerchandiseAttribute merchandiseAttribute);

	@Delete("delete from `commoditycenter_merchandise_attribute` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_merchandise_attribute` limit #{start},#{count}")
	public List<MerchandiseAttribute> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_merchandise_attribute`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_merchandise_attribute`")
	public List<MerchandiseAttribute> listAll();
}