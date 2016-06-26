package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.query.MerchandiseImageQuery;

public interface MerchandiseImageMapper {

	@Insert("insert into `goods_merchandise_image`(`id`,`merchandiseId`,`attributeId`,`value`,`image`)"
			+ " values(#{id},#{merchandiseId},#{attributeId},#{value},#{image})")
	public void insert(MerchandiseImage merchandiseImage);

	@Select("select * from `goods_merchandise_image` where `id`=#{id}")
	public MerchandiseImage get(Long id);

	@Update("update `goods_merchandise_image` set `merchandiseId`=#{merchandiseId},`attributeId`=#{attributeId},`value`=#{value},`image`=#{image} where `id`=#{id}")
	public void update(MerchandiseImage merchandiseImage);

	@Delete("delete from `goods_merchandise_image` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_image` limit #{start},#{count}")
	public List<MerchandiseImage> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_image`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_image`")
	public List<MerchandiseImage> listAll();
}