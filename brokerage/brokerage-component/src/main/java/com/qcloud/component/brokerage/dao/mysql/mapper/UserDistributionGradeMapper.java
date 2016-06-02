package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;

public interface UserDistributionGradeMapper {

	@Insert("insert into `brokerage_user_distribution_grade`(`id`,`userId`,`gradeId`,`createTime`,`upgradeTime`,`effectiveTime`)"
			+ " values(#{id},#{userId},#{gradeId},#{createTime},#{upgradeTime},#{effectiveTime})")
	public void insert(UserDistributionGrade userDistributionGrade);

	@Select("select * from `brokerage_user_distribution_grade` where `id`=#{id}")
	public UserDistributionGrade get(Long id);

	@Update("update `brokerage_user_distribution_grade` set `userId`=#{userId},`gradeId`=#{gradeId},`createTime`=#{createTime},`upgradeTime`=#{upgradeTime},`effectiveTime`=#{effectiveTime} where `id`=#{id}")
	public void update(UserDistributionGrade userDistributionGrade);

	@Delete("delete from `brokerage_user_distribution_grade` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_user_distribution_grade` limit #{start},#{count}")
	public List<UserDistributionGrade> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_user_distribution_grade`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_user_distribution_grade`")
	public List<UserDistributionGrade> listAll();
}