package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;

public interface ReturnOrderMapper {

	@Insert("insert into `orderform_return_order`(`id`,`orderId`,`orderDate`,`orderNumber`,`subOrderId`,`merchantId`,`storeId`,`sum`,`state`,`userId`,`time`,`returnNumber`,`explain`,`reason`,`logisticsCompany`,`logisticsNumber`,`lastUpdateTime`,`stateValidTime`)"
			+ " values(#{id},#{orderId},#{orderDate},#{orderNumber},#{subOrderId},#{merchantId},#{storeId},#{sum},#{state},#{userId},#{time},#{returnNumber},#{explain},#{reason},#{logisticsCompany},#{logisticsNumber},#{lastUpdateTime},#{stateValidTime})")
	public void insert(ReturnOrder returnOrder);

	@Select("select * from `orderform_return_order` where `id`=#{id}")
	public ReturnOrder get(Long id);

	@Update("update `orderform_return_order` set `orderId`=#{orderId},`orderDate`=#{orderDate},`orderNumber`=#{orderNumber},`subOrderId`=#{subOrderId},`merchantId`=#{merchantId},`storeId`=#{storeId},`sum`=#{sum},`state`=#{state},`userId`=#{userId},`time`=#{time},`returnNumber`=#{returnNumber},`explain`=#{explain},`reason`=#{reason},`logisticsCompany`=#{logisticsCompany},`logisticsNumber`=#{logisticsNumber},`lastUpdateTime`=#{lastUpdateTime},`stateValidTime`=#{stateValidTime} where `id`=#{id}")
	public void update(ReturnOrder returnOrder);

	@Delete("delete from `orderform_return_order` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_return_order` limit #{start},#{count}")
	public List<ReturnOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_return_order`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_return_order`")
	public List<ReturnOrder> listAll();
}