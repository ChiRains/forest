package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;

public interface DataPoolMapper {

	@Insert("insert into `brokerage_data_pool`(`id`,`sourceDateId`,`type`,`name`,`image`,`purchase`,`discount`,`number`,`cash`,`orderTime`,`userId`,`merchantId`,`generateTime`,`formulaId`)"
			+ " values(#{id},#{sourceDateId},#{type},#{name},#{image},#{purchase},#{discount},#{number},#{cash},#{orderTime},#{userId},#{merchantId},#{generateTime},#{formulaId})")
	public void insert(DataPool dataPool);

	@Select("select * from `brokerage_data_pool` where `id`=#{id}")
	public DataPool get(Long id);

	@Update("update `brokerage_data_pool` set `sourceDateId`=#{sourceDateId},`type`=#{type},`name`=#{name},`image`=#{image},`purchase`=#{purchase},`discount`=#{discount},`number`=#{number},`cash`=#{cash},`orderTime`=#{orderTime},`userId`=#{userId},`merchantId`=#{merchantId},`generateTime`=#{generateTime},`formulaId`=#{formulaId} where `id`=#{id}")
	public void update(DataPool dataPool);

	@Delete("delete from `brokerage_data_pool` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_data_pool` limit #{start},#{count}")
	public List<DataPool> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_data_pool`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_data_pool`")
	public List<DataPool> listAll();
}