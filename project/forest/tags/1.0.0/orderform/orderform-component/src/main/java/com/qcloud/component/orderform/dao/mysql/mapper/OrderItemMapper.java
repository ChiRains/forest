package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.OrderItemQuery;

public interface OrderItemMapper {

	@Insert("insert into `${table_name}`(`id`,`orderId`,`subOrderId`,`merchantId`,`unifiedMerchandiseId`,`sence`,`name`,`image`,`purchase`,`discount`,`price`,`preferential`,`sum`,`cash`,`integral`,`consumption`,`preferentialStr`,`number`,`state`,`snapshot`,`evaluation`,`afterSale`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{merchantId},#{unifiedMerchandiseId},#{sence},#{name},#{image},#{purchase},#{discount},#{price},#{preferential},#{sum},#{cash},#{integral},#{consumption},#{preferentialStr},#{number},#{state},#{snapshot},#{evaluation},#{afterSale})")
	public void insert(OrderItem orderItem);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public OrderItem get(Long id);

	@Update("update `${table_name}` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`merchantId`=#{merchantId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`sence`=#{sence},`name`=#{name},`image`=#{image},`purchase`=#{purchase},`discount`=#{discount},`price`=#{price},`preferential`=#{preferential},`sum`=#{sum},`cash`=#{cash},`integral`=#{integral},`consumption`=#{consumption},`preferentialStr`=#{preferentialStr},`number`=#{number},`state`=#{state},`snapshot`=#{snapshot},`evaluation`=#{evaluation},`afterSale`=#{afterSale} where `id`=#{id}")
	public void update(OrderItem orderItem);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<OrderItem> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<OrderItem> listAll();
}