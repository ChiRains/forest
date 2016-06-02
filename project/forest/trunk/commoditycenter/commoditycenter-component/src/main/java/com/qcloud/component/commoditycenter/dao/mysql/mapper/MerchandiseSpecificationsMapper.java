package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.MerchandiseSpecifications;
import com.qcloud.component.commoditycenter.model.query.MerchandiseSpecificationsQuery;

public interface MerchandiseSpecificationsMapper {

	@Insert("insert into `commoditycenter_merchandise_specifications`(`id`,`merchandiseId`,`attributeId0`,`value0`,`attributeId1`,`value1`,`attributeId2`,`value2`,`state`)"
			+ " values(#{id},#{merchandiseId},#{attributeId0},#{value0},#{attributeId1},#{value1},#{attributeId2},#{value2},#{state})")
	public void insert(MerchandiseSpecifications merchandiseSpecifications);

	@Select("select * from `commoditycenter_merchandise_specifications` where `id`=#{id}")
	public MerchandiseSpecifications get(Long id);

	@Update("update `commoditycenter_merchandise_specifications` set `merchandiseId`=#{merchandiseId},`attributeId0`=#{attributeId0},`value0`=#{value0},`attributeId1`=#{attributeId1},`value1`=#{value1},`attributeId2`=#{attributeId2},`value2`=#{value2},`state`=#{state} where `id`=#{id}")
	public void update(MerchandiseSpecifications merchandiseSpecifications);

	@Delete("delete from `commoditycenter_merchandise_specifications` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_merchandise_specifications` limit #{start},#{count}")
	public List<MerchandiseSpecifications> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_merchandise_specifications`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_merchandise_specifications`")
	public List<MerchandiseSpecifications> listAll();
}