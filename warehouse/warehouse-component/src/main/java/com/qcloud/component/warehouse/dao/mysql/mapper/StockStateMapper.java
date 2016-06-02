package com.qcloud.component.warehouse.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.model.query.StockStateQuery;

public interface StockStateMapper {

	@Insert("insert into `warehouse_stock_state`(`id`,`formStoreId`,`toStoreId`,`state`)"
			+ " values(#{id},#{formStoreId},#{toStoreId},#{state})")
	public void insert(StockState stockState);

	@Select("select * from `warehouse_stock_state` where `id`=#{id}")
	public StockState get(Long id);

	@Update("update `warehouse_stock_state` set `formStoreId`=#{formStoreId},`toStoreId`=#{toStoreId},`state`=#{state} where `id`=#{id}")
	public void update(StockState stockState);

	@Delete("delete from `warehouse_stock_state` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `warehouse_stock_state` limit #{start},#{count}")
	public List<StockState> list4page(Map<String,Object> param);

	@Select("select count(*) from `warehouse_stock_state`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `warehouse_stock_state`")
	public List<StockState> listAll();
}