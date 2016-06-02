package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.query.MyAfterSaleQuery;

public interface MyAfterSaleMapper {

	@Insert("insert into `my_my_after_sale`(`id`,`userId`,`type`,`time`,`lastUpdateTime`,`afterSaleId`,`view`,`orderId`,`subOrderId`)"
			+ " values(#{id},#{userId},#{type},#{time},#{lastUpdateTime},#{afterSaleId},#{view},#{orderId},#{subOrderId})")
	public void insert(MyAfterSale myAfterSale);

	@Select("select * from `my_my_after_sale` where `id`=#{id}")
	public MyAfterSale get(Long id);

	@Update("update `my_my_after_sale` set `userId`=#{userId},`type`=#{type},`time`=#{time},`lastUpdateTime`=#{lastUpdateTime},`afterSaleId`=#{afterSaleId},`view`=#{view},`orderId`=#{orderId},`subOrderId`=#{subOrderId} where `id`=#{id}")
	public void update(MyAfterSale myAfterSale);

	@Delete("delete from `my_my_after_sale` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_my_after_sale` limit #{start},#{count}")
	public List<MyAfterSale> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_my_after_sale`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_my_after_sale`")
	public List<MyAfterSale> listAll();
}