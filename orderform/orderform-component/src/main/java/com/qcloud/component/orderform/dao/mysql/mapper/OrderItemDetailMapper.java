package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;

public interface OrderItemDetailMapper {

	@Insert("insert into `${table_name}`(`id`,`orderId`,`subOrderId`,`orderItemId`,`merchantId`,`unifiedMerchandiseId`,`merchandiseItemId`,`number`,`name`,`state`,`specifications`,`image`,`code`,`weight`,`discount`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{orderItemId},#{merchantId},#{unifiedMerchandiseId},#{merchandiseItemId},#{number},#{name},#{state},#{specifications},#{image},#{code},#{weight},#{discount})")
	public void insert(OrderItemDetail orderItemDetail);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public OrderItemDetail get(Long id);

	@Update("update `${table_name}` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`merchantId`=#{merchantId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`merchandiseItemId`=#{merchandiseItemId},`number`=#{number},`name`=#{name},`state`=#{state},`specifications`=#{specifications},`image`=#{image},`code`=#{code},`weight`=#{weight},`discount`=#{discount}  where `id`=#{id}")
	public void update(OrderItemDetail orderItemDetail);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<OrderItemDetail> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<OrderItemDetail> listAll();
}