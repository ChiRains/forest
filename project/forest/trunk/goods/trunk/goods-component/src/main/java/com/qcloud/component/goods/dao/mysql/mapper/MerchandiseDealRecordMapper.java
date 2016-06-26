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

	@Insert("insert into `goods_merchandise_deal_record`(`id`,`merchandiseId`,`userId`,`number`,`specifications`,`time`,`orderId`,`subOrderId`,`orderItemId`,`orderItemDetailId`)"
			+ " values(#{id},#{merchandiseId},#{userId},#{number},#{specifications},#{time},#{orderId},#{subOrderId},#{orderItemId},#{orderItemDetailId})")
	public void insert(MerchandiseDealRecord merchandiseDealRecord);

	@Select("select * from `goods_merchandise_deal_record` where `id`=#{id}")
	public MerchandiseDealRecord get(Long id);

	@Update("update `goods_merchandise_deal_record` set `merchandiseId`=#{merchandiseId},`userId`=#{userId},`number`=#{number},`specifications`=#{specifications},`time`=#{time},`orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`orderItemDetailId`=#{orderItemDetailId} where `id`=#{id}")
	public void update(MerchandiseDealRecord merchandiseDealRecord);

	@Delete("delete from `goods_merchandise_deal_record` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_deal_record` limit #{start},#{count}")
	public List<MerchandiseDealRecord> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_deal_record`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_deal_record`")
	public List<MerchandiseDealRecord> listAll();
}