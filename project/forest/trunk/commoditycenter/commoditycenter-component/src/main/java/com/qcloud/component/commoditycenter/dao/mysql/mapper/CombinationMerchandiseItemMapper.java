package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.CombinationMerchandiseItem;
import com.qcloud.component.commoditycenter.model.query.CombinationMerchandiseItemQuery;

public interface CombinationMerchandiseItemMapper {

	@Insert("insert into `commoditycenter_combination_merchandise_item`(`id`,`combinationMerchandiseId`,`merchandiseItemId`,`merchantId`,`num`)"
			+ " values(#{id},#{combinationMerchandiseId},#{merchandiseItemId},#{merchantId},#{num})")
	public void insert(CombinationMerchandiseItem combinationMerchandiseItem);

	@Select("select * from `commoditycenter_combination_merchandise_item` where `id`=#{id}")
	public CombinationMerchandiseItem get(Long id);

	@Update("update `commoditycenter_combination_merchandise_item` set `combinationMerchandiseId`=#{combinationMerchandiseId},`merchandiseItemId`=#{merchandiseItemId},`num`=#{num} where `id`=#{id}")
	public void update(CombinationMerchandiseItem combinationMerchandiseItem);

	@Delete("delete from `commoditycenter_combination_merchandise_item` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_combination_merchandise_item` limit #{start},#{count}")
	public List<CombinationMerchandiseItem> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_combination_merchandise_item`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_combination_merchandise_item`")
	public List<CombinationMerchandiseItem> listAll();
}