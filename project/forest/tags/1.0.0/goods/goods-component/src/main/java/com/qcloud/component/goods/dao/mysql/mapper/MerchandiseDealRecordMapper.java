package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.model.query.MerchandiseDealRecordQuery;

public interface MerchandiseDealRecordMapper {

	@Insert("insert into `${table_name}`(`id`,`merchandiseId`,`userId`,`number`,`specifications`,`time`,`orderId`,`subOrderId`,`orderItemId`,`orderItemDetailId`)"
			+ " values(#{id},#{merchandiseId},#{userId},#{number},#{specifications},#{time},#{orderId},#{subOrderId},#{orderItemId},#{orderItemDetailId})")
	public void insert(MerchandiseDealRecord merchandiseDealRecord);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MerchandiseDealRecord get(Long id);

	@Update("update `${table_name}` set `merchandiseId`=#{merchandiseId},`userId`=#{userId},`number`=#{number},`specifications`=#{specifications},`time`=#{time},`orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`orderItemDetailId`=#{orderItemDetailId} where `id`=#{id}")
	public void update(MerchandiseDealRecord merchandiseDealRecord);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MerchandiseDealRecord> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MerchandiseDealRecord> listAll();
}