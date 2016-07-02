package com.qcloud.component.pay.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.model.query.PayRequestQuery;

public interface PayRequestMapper {

	@Insert("insert into `pay_pay_request`(`id`,`objectId`,`occurTime`,`objectNumber`,`tradeId`,`module`,`type`,`client`,`cash`,`attach`)"
			+ " values(#{id},#{objectId},#{occurTime},#{objectNumber},#{tradeId},#{module},#{type},#{client},#{cash},#{attach})")
	public void insert(PayRequest payRequest);

	@Select("select * from `pay_pay_request` where `id`=#{id}")
	public PayRequest get(Long id);

	@Update("update `pay_pay_request` set `objectId`=#{objectId},`occurTime`=#{occurTime},`objectNumber`=#{objectNumber},`tradeId`=#{tradeId},`module`=#{module},`type`=#{type},`client`=#{client},`cash`=#{cash},`attach`=#{attach} where `id`=#{id}")
	public void update(PayRequest payRequest);

	@Delete("delete from `pay_pay_request` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `pay_pay_request` limit #{start},#{count}")
	public List<PayRequest> list4page(Map<String,Object> param);

	@Select("select count(*) from `pay_pay_request`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `pay_pay_request`")
	public List<PayRequest> listAll();
}