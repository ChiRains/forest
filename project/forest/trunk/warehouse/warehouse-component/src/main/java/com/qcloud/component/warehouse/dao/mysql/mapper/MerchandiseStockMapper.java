package com.qcloud.component.warehouse.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;

public interface MerchandiseStockMapper {

	@Insert("insert into `warehouse_merchandise_stock`(`id`,`merchantId`,`storeId`,`unifiedMerchandiseId`,`merchandiseName`,`purchase`,`discount`,`price`,`stock`)"
			+ " values(#{id},#{merchantId},#{storeId},#{unifiedMerchandiseId},#{merchandiseName},#{purchase},#{discount},#{price},#{stock})")
	public void insert(MerchandiseStock merchandiseStock);

	@Select("select * from `warehouse_merchandise_stock` where `id`=#{id}")
	public MerchandiseStock get(Long id);

	@Update("update `warehouse_merchandise_stock` set `merchantId`=#{merchantId},`storeId`=#{storeId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`merchandiseName`=#{merchandiseName},`purchase`=#{purchase},`discount`=#{discount},`price`=#{price},`stock`=#{stock} where `id`=#{id}")
	public void update(MerchandiseStock merchandiseStock);

	@Delete("delete from `warehouse_merchandise_stock` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `warehouse_merchandise_stock` limit #{start},#{count}")
	public List<MerchandiseStock> list4page(Map<String,Object> param);

	@Select("select count(*) from `warehouse_merchandise_stock`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `warehouse_merchandise_stock`")
	public List<MerchandiseStock> listAll();
}