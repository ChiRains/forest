package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.ClassifyAttribute;
import com.qcloud.component.commoditycenter.model.query.ClassifyAttributeQuery;

public interface ClassifyAttributeMapper {

	@Insert("insert into `commoditycenter_classify_attribute`(`id`,`classifyId`,`attributeId`,`sort`)"
			+ " values(#{id},#{classifyId},#{attributeId},#{sort})")
	public void insert(ClassifyAttribute classifyAttribute);

	@Select("select * from `commoditycenter_classify_attribute` where `id`=#{id}")
	public ClassifyAttribute get(Long id);

	@Update("update `commoditycenter_classify_attribute` set `classifyId`=#{classifyId},`attributeId`=#{attributeId},`sort`=#{sort} where `id`=#{id}")
	public void update(ClassifyAttribute classifyAttribute);

	@Delete("delete from `commoditycenter_classify_attribute` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_classify_attribute` limit #{start},#{count}")
	public List<ClassifyAttribute> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_classify_attribute`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_classify_attribute`")
	public List<ClassifyAttribute> listAll();
}