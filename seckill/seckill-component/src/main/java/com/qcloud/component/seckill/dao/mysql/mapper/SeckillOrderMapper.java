package com.qcloud.component.seckill.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.seckill.model.SeckillOrder;
import com.qcloud.component.seckill.model.query.SeckillOrderQuery;

public interface SeckillOrderMapper {

	@Insert("insert into `seckill_seckill_order`(`id`,`orderNumber`,`seckillMerchandiseId`,`merchantId`,`orderId`,`time`,`sum`,`cash`,`userId`,`unifiedMerchandiseId`,`name`,`image`,`purchase`,`discount`,`price`,`seckillPrice`)"
			+ " values(#{id},#{orderNumber},#{seckillMerchandiseId},#{merchantId},#{orderId},#{time},#{sum},#{cash},#{userId},#{unifiedMerchandiseId},#{name},#{image},#{purchase},#{discount},#{price},#{seckillPrice})")
	public void insert(SeckillOrder seckillOrder);

	@Select("select * from `seckill_seckill_order` where `id`=#{id}")
	public SeckillOrder get(Long id);

	@Update("update `seckill_seckill_order` set `orderNumber`=#{orderNumber},`seckillMerchandiseId`=#{seckillMerchandiseId},`merchantId`=#{merchantId},`orderId`=#{orderId},`time`=#{time},`sum`=#{sum},`cash`=#{cash},`userId`=#{userId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`name`=#{name},`image`=#{image},`purchase`=#{purchase},`discount`=#{discount},`price`=#{price},`seckillPrice`=#{seckillPrice} where `id`=#{id}")
	public void update(SeckillOrder seckillOrder);

	@Delete("delete from `seckill_seckill_order` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `seckill_seckill_order` limit #{start},#{count}")
	public List<SeckillOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `seckill_seckill_order`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `seckill_seckill_order`")
	public List<SeckillOrder> listAll();
}