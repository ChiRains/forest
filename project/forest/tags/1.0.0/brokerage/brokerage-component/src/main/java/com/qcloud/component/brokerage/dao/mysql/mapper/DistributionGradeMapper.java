package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;

public interface DistributionGradeMapper {

	@Insert("insert into `brokerage_distribution_grade`(`id`,`name`,`monthLimit`,`desc`,`cash`,`userResource`,`classifyId`,`bsid`,`type`)"
			+ " values(#{id},#{name},#{monthLimit},#{desc},#{cash},#{userResource},#{classifyId},#{bsid},#{type})")
	public void insert(DistributionGrade distributionGrade);

	@Select("select * from `brokerage_distribution_grade` where `id`=#{id}")
	public DistributionGrade get(Long id);

	@Update("update `brokerage_distribution_grade` set `name`=#{name},`monthLimit`=#{monthLimit},`desc`=#{desc},`cash`=#{cash},`userResource`=#{userResource},`classifyId`=#{classifyId},`bsid`=#{bsid},`type`=#{type} where `id`=#{id}")
	public void update(DistributionGrade distributionGrade);

	@Delete("delete from `brokerage_distribution_grade` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_distribution_grade` limit #{start},#{count}")
	public List<DistributionGrade> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_distribution_grade`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_distribution_grade`")
	public List<DistributionGrade> listAll();
}