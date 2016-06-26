package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;

public interface MerchandiseSpecificationsMapper {

	@Insert("insert into `goods_merchandise_specifications`(`id`,`merchandiseId`,`attributeId0`,`value0`,`attributeId1`,`value1`,`attributeId2`,`value2`,`state`)"
			+ " values(#{id},#{merchandiseId},#{attributeId0},#{value0},#{attributeId1},#{value1},#{attributeId2},#{value2},#{state})")
	public void insert(MerchandiseSpecifications merchandiseSpecifications);

	@Select("select * from `goods_merchandise_specifications` where `id`=#{id}")
	public MerchandiseSpecifications get(Long id);

	@Update("update `goods_merchandise_specifications` set `merchandiseId`=#{merchandiseId},`attributeId0`=#{attributeId0},`value0`=#{value0},`attributeId1`=#{attributeId1},`value1`=#{value1},`attributeId2`=#{attributeId2},`value2`=#{value2},`state`=#{state} where `id`=#{id}")
	public void update(MerchandiseSpecifications merchandiseSpecifications);

	@Delete("delete from `goods_merchandise_specifications` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_specifications` limit #{start},#{count}")
	public List<MerchandiseSpecifications> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_specifications`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_specifications`")
	public List<MerchandiseSpecifications> listAll();
}