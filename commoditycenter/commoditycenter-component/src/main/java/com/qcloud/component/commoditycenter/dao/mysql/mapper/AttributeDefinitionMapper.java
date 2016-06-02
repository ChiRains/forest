package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.query.AttributeDefinitionQuery;

public interface AttributeDefinitionMapper {

	@Insert("insert into `commoditycenter_attribute_definition`(`id`,`name`,`code`,`remark`,`enumeration`,`value`,`type`,`valueType`)"
			+ " values(#{id},#{name},#{code},#{remark},#{enumeration},#{value},#{type},#{valueType})")
	public void insert(AttributeDefinition attributeDefinition);

	@Select("select * from `commoditycenter_attribute_definition` where `id`=#{id}")
	public AttributeDefinition get(Long id);

	@Update("update `commoditycenter_attribute_definition` set `name`=#{name},`code`=#{code},`remark`=#{remark},`enumeration`=#{enumeration},`value`=#{value},`type`=#{type},`valueType`=#{valueType} where `id`=#{id}")
	public void update(AttributeDefinition attributeDefinition);

	@Delete("delete from `commoditycenter_attribute_definition` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_attribute_definition` limit #{start},#{count}")
	public List<AttributeDefinition> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_attribute_definition`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_attribute_definition`")
	public List<AttributeDefinition> listAll();
}