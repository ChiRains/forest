package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.orderform.model.SubOrder;

public interface SubOrderMapper {

	@Insert("insert into `${table_name}`(`id`,`orderId`,`merchantId`,`storeId`,`orderNumber`,`sum`,`cash`,`integral`,`coupon`,`postage`,`consumption`,`preferential`,`state`,`prestate`,`explain`,`pickupAddressStr`,`deliveryTimeStr`,`deliveryMode`,`expressCode`,`expressName`,`expressNumber`,`deliveryTime`)"
			+ " values(#{id},#{orderId},#{merchantId},#{storeId},#{orderNumber},#{sum},#{cash},#{integral},#{coupon},#{postage},#{consumption},#{preferential},#{state},#{prestate},#{explain},#{pickupAddressStr},#{deliveryTimeStr},#{deliveryMode},#{expressCode},#{expressName},#{expressNumber},#{deliveryTime})")
	public void insert(SubOrder subOrder);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public SubOrder get(Long id);

	@Update("update `${table_name}` set `orderId`=#{orderId},`merchantId`=#{merchantId},`storeId`=#{storeId},`orderNumber`=#{orderNumber},`sum`=#{sum},`cash`=#{cash},`integral`=#{integral},`coupon`=#{coupon},`postage`=#{postage},`consumption`=#{consumption},`preferential`=#{preferential},`state`=#{state},`prestate`=#{prestate},`explain`=#{explain},`pickupAddressStr`=#{pickupAddressStr},`deliveryTimeStr`=#{deliveryTimeStr},`deliveryMode`=#{deliveryMode},`expressCode`=#{expressCode},`expressName`=#{expressName},`expressNumber`=#{expressNumber},`deliveryTime`=#{deliveryTime} where `id`=#{id}")
	public void update(SubOrder subOrder);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<SubOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<SubOrder> listAll();
}