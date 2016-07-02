package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;

public interface DistributionBrokerageMapper {

	@Insert("insert into `brokerage_distribution_brokerage`(`id`,`owner`,`resultId`,`gradeId`,`userId`,`route`,`proportion`,`poundageRate`,`type`,`name`,`image`,`brokerage`,`poundage`,`formulaId`,`state`,`auditTime`,`orderTime`)"
			+ " values(#{id},#{owner},#{resultId},#{gradeId},#{userId},#{route},#{proportion},#{poundageRate},#{type},#{name},#{image},#{brokerage},#{poundage},#{formulaId},#{state},#{auditTime},#{orderTime})")
	public void insert(DistributionBrokerage distributionBrokerage);

	@Select("select * from `brokerage_distribution_brokerage` where `id`=#{id}")
	public DistributionBrokerage get(Long id);

	@Update("update `brokerage_distribution_brokerage` set `owner`=#{owner},`resultId`=#{resultId},`gradeId`=#{gradeId},`userId`=#{userId},`route`=#{route},`proportion`=#{proportion},`poundageRate`=#{poundageRate},`type`=#{type},`name`=#{name},`image`=#{image},`brokerage`=#{brokerage},`poundage`=#{poundage},`formulaId`=#{formulaId},`state`=#{state},`auditTime`=#{auditTime},`orderTime`=#{orderTime} where `id`=#{id}")
	public void update(DistributionBrokerage distributionBrokerage);

	@Delete("delete from `brokerage_distribution_brokerage` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_distribution_brokerage` limit #{start},#{count}")
	public List<DistributionBrokerage> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_distribution_brokerage`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_distribution_brokerage`")
	public List<DistributionBrokerage> listAll();
}