package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.CombinationMerchandise;
import com.qcloud.component.commoditycenter.model.query.CombinationMerchandiseQuery;

public interface CombinationMerchandiseMapper {

	@Insert("insert into `commoditycenter_combination_merchandise`(`id`,`merchantId`,`name`,`unifiedMerchandiseId`,`purchase`,`discount`,`price`,`stock`,`updateTime`,`image`)"
			+ " values(#{id},#{merchantId},#{name},#{unifiedMerchandiseId},#{purchase},#{discount},#{price},#{stock},#{updateTime},#{image})")
	public void insert(CombinationMerchandise combinationMerchandise);

	@Select("select * from `commoditycenter_combination_merchandise` where `id`=#{id}")
	public CombinationMerchandise get(Long id);

	@Update("update `commoditycenter_combination_merchandise` set `merchantId`=#{merchantId},`name`=#{name},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`purchase`=#{purchase},`discount`=#{discount},`price`=#{price},`stock`=#{stock},`updateTime`=#{updateTime},`image`=#{image} where `id`=#{id}")
	public void update(CombinationMerchandise combinationMerchandise);

	@Delete("delete from `commoditycenter_combination_merchandise` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_combination_merchandise` limit #{start},#{count}")
	public List<CombinationMerchandise> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_combination_merchandise`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_combination_merchandise`")
	public List<CombinationMerchandise> listAll();
}