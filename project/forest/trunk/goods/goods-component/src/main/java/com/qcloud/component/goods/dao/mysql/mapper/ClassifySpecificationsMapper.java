package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;

public interface ClassifySpecificationsMapper {

	@Insert("insert into `goods_classify_specifications`(`id`,`classifyId`,`attributeId`,`uploadImage`,`sort`)"
			+ " values(#{id},#{classifyId},#{attributeId},#{uploadImage},#{sort})")
	public void insert(ClassifySpecifications classifySpecifications);

	@Select("select * from `goods_classify_specifications` where `id`=#{id}")
	public ClassifySpecifications get(Long id);

	@Update("update `goods_classify_specifications` set `classifyId`=#{classifyId},`attributeId`=#{attributeId},`uploadImage`=#{uploadImage},`sort`=#{sort} where `id`=#{id}")
	public void update(ClassifySpecifications classifySpecifications);

	@Delete("delete from `goods_classify_specifications` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_classify_specifications` limit #{start},#{count}")
	public List<ClassifySpecifications> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_classify_specifications`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_classify_specifications`")
	public List<ClassifySpecifications> listAll();
}