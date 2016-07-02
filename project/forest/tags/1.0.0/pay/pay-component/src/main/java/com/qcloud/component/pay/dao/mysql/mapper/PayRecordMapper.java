package com.qcloud.component.pay.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.model.query.PayRecordQuery;

public interface PayRecordMapper {

	@Insert("insert into `pay_pay_record`(`id`,`objectId`,`occurTime`,`tradeId`,`module`,`type`,`client`,`attach`,`time`)"
			+ " values(#{id},#{objectId},#{occurTime},#{tradeId},#{module},#{type},#{client},#{attach},#{time})")
	public void insert(PayRecord payRecord);

	@Select("select * from `pay_pay_record` where `id`=#{id}")
	public PayRecord get(Long id);

	@Update("update `pay_pay_record` set `objectId`=#{objectId},`occurTime`=#{occurTime},`tradeId`=#{tradeId},`module`=#{module},`type`=#{type},`client`=#{client},`attach`=#{attach},`time`=#{time} where `id`=#{id}")
	public void update(PayRecord payRecord);

	@Delete("delete from `pay_pay_record` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `pay_pay_record` limit #{start},#{count}")
	public List<PayRecord> list4page(Map<String,Object> param);

	@Select("select count(*) from `pay_pay_record`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `pay_pay_record`")
	public List<PayRecord> listAll();
}