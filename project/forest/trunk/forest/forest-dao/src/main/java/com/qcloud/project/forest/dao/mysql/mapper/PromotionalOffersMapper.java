package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;

public interface PromotionalOffersMapper {

	@Insert("insert into `forest_promotional_offers`(`id`,`classify`,`image`,`name`,`price`,`time`)"
			+ " values(#{id},#{classify},#{image},#{name},#{price},#{time})")
	public void insert(PromotionalOffers promotionalOffers);

	@Select("select * from `forest_promotional_offers` where `id`=#{id}")
	public PromotionalOffers get(Long id);

	@Update("update `forest_promotional_offers` set `classify`=#{classify},`image`=#{image},`name`=#{name},`price`=#{price},`time`=#{time} where `id`=#{id}")
	public void update(PromotionalOffers promotionalOffers);

	@Delete("delete from `forest_promotional_offers` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_promotional_offers` limit #{start},#{count}")
	public List<PromotionalOffers> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_promotional_offers`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_promotional_offers`")
	public List<PromotionalOffers> listAll();
}