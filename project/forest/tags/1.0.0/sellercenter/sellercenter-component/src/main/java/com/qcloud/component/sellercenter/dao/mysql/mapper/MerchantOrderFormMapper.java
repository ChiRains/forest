package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;

public interface MerchantOrderFormMapper {

	@Insert("insert into `${table_name}`(`id`,`merchantId`,`storeId`,`orderId`,`subOrderId`,`state`,`time`)"
			+ " values(#{id},#{merchantId},#{storeId},#{orderId},#{subOrderId},#{state},#{time})")
	public void insert(MerchantOrderForm merchantOrderForm);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MerchantOrderForm get(Long id);

	@Update("update `${table_name}` set `merchantId`=#{merchantId},`storeId`=#{storeId},`orderId`=#{orderId},`subOrderId`=#{subOrderId},`state`=#{state},`time`=#{time} where `id`=#{id}")
	public void update(MerchantOrderForm merchantOrderForm);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MerchantOrderForm> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MerchantOrderForm> listAll();
}