package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;

public interface ExchangeOrderMapper {

	@Insert("insert into `orderform_exchange_order`(`id`,`orderNumber`,`orderId`,`orderDate`,`subOrderId`,`merchantId`,`storeId`,`state`,`time`,`userId`,`explain`,`reason`,`exchangeNumber`,`userLogisticsCompany`,`userLogisticsNumber`,`merchantLogisticsCompany`,`merchantLogisticsNumber`,`lastUpdateTime`,`stateValidTime`)"
			+ " values(#{id},#{orderNumber},#{orderId},#{orderDate},#{subOrderId},#{merchantId},#{storeId},#{state},#{time},#{userId},#{explain},#{reason},#{exchangeNumber},#{userLogisticsCompany},#{userLogisticsNumber},#{merchantLogisticsCompany},#{merchantLogisticsNumber},#{lastUpdateTime},#{stateValidTime})")
	public void insert(ExchangeOrder exchangeOrder);

	@Select("select * from `orderform_exchange_order` where `id`=#{id}")
	public ExchangeOrder get(Long id);

	@Update("update `orderform_exchange_order` set `orderNumber`=#{orderNumber},`orderId`=#{orderId},`orderDate`=#{orderDate},`subOrderId`=#{subOrderId},`merchantId`=#{merchantId},`storeId`=#{storeId},`state`=#{state},`time`=#{time},`userId`=#{userId},`explain`=#{explain},`reason`=#{reason},`exchangeNumber`=#{exchangeNumber},`userLogisticsCompany`=#{userLogisticsCompany},`userLogisticsNumber`=#{userLogisticsNumber},`merchantLogisticsCompany`=#{merchantLogisticsCompany},`merchantLogisticsNumber`=#{merchantLogisticsNumber},`lastUpdateTime`=#{lastUpdateTime},`stateValidTime`=#{stateValidTime} where `id`=#{id}")
	public void update(ExchangeOrder exchangeOrder);

	@Delete("delete from `orderform_exchange_order` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_exchange_order` limit #{start},#{count}")
	public List<ExchangeOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_exchange_order`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_exchange_order`")
	public List<ExchangeOrder> listAll();
}