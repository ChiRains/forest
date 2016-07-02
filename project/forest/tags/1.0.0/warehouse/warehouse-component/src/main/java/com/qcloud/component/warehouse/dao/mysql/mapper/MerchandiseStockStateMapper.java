package com.qcloud.component.warehouse.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;

public interface MerchandiseStockStateMapper {

	@Insert("insert into `warehouse_merchandise_stock_state`(`id`,`stockStateId`,`unifiedMerchandiseId`,`number`,`state`,`applyTime`,`confirmTime`,`signTime`)"
			+ " values(#{id},#{stockStateId},#{unifiedMerchandiseId},#{number},#{state},#{applyTime},#{confirmTime},#{signTime})")
	public void insert(MerchandiseStockState merchandiseStockState);

	@Select("select * from `warehouse_merchandise_stock_state` where `id`=#{id}")
	public MerchandiseStockState get(Long id);

	@Update("update `warehouse_merchandise_stock_state` set `stockStateId`=#{stockStateId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`number`=#{number},`state`=#{state},`applyTime`=#{applyTime},`confirmTime`=#{confirmTime},`signTime`=#{signTime} where `id`=#{id}")
	public void update(MerchandiseStockState merchandiseStockState);

	@Delete("delete from `warehouse_merchandise_stock_state` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `warehouse_merchandise_stock_state` limit #{start},#{count}")
	public List<MerchandiseStockState> list4page(Map<String,Object> param);

	@Select("select count(*) from `warehouse_merchandise_stock_state`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `warehouse_merchandise_stock_state`")
	public List<MerchandiseStockState> listAll();
}