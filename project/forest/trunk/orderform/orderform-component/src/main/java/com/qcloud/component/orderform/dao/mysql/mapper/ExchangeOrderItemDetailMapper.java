package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;

public interface ExchangeOrderItemDetailMapper {

	@Insert("insert into `orderform_exchange_order_item_detail`(`id`,`orderId`,`subOrderId`,`orderItemId`,`orderItemDetailId`,`time`,`state`,`number`,`explain`,`reason`,`exchangeId`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{orderItemId},#{orderItemDetailId},#{time},#{state},#{number},#{explain},#{reason},#{exchangeId})")
	public void insert(ExchangeOrderItemDetail exchangeOrderItemDetail);

	@Select("select * from `orderform_exchange_order_item_detail` where `id`=#{id}")
	public ExchangeOrderItemDetail get(Long id);

	@Update("update `orderform_exchange_order_item_detail` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`orderItemDetailId`=#{orderItemDetailId},`time`=#{time},`state`=#{state},`number`=#{number},`explain`=#{explain},`reason`=#{reason},`exchangeId`=#{exchangeId} where `id`=#{id}")
	public void update(ExchangeOrderItemDetail exchangeOrderItemDetail);

	@Delete("delete from `orderform_exchange_order_item_detail` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_exchange_order_item_detail` limit #{start},#{count}")
	public List<ExchangeOrderItemDetail> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_exchange_order_item_detail`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_exchange_order_item_detail`")
	public List<ExchangeOrderItemDetail> listAll();
}