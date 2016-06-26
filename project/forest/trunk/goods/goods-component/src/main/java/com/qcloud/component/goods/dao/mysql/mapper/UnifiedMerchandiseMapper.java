package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;

public interface UnifiedMerchandiseMapper {

	@Insert("insert into `goods_unified_merchandise`(`id`,`merchantId`,`type`)"
			+ " values(#{id},#{merchantId},#{type})")
	public void insert(UnifiedMerchandise unifiedMerchandise);

	@Select("select * from `goods_unified_merchandise` where `id`=#{id}")
	public UnifiedMerchandise get(Long id);

	@Update("update `goods_unified_merchandise` set `merchantId`=#{merchantId},`type`=#{type} where `id`=#{id}")
	public void update(UnifiedMerchandise unifiedMerchandise);

	@Delete("delete from `goods_unified_merchandise` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_unified_merchandise` limit #{start},#{count}")
	public List<UnifiedMerchandise> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_unified_merchandise`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_unified_merchandise`")
	public List<UnifiedMerchandise> listAll();
}