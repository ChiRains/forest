package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.MedicationTime;

public interface MedicationTimeMapper {

	@Insert("insert into `forest_medication_time`(`id`,`medicationId`,`takeTime`,`enable`,`userId`,`excuteTime`,`recordTime`,`endTime`,`periodType`)"
			+ " values(#{id},#{medicationId},#{takeTime},#{enable},#{userId},#{excuteTime},#{recordTime},#{endTime},#{periodType})")
	public void insert(MedicationTime medicationTime);

	@Select("select * from `forest_medication_time` where `id`=#{id}")
	public MedicationTime get(Long id);

	@Update("update `forest_medication_time` set `medicationId`=#{medicationId},`takeTime`=#{takeTime},`enable`=#{enable},`userId`=#{userId},`excuteTime`=#{excuteTime},`recordTime`=#{recordTime},`endTime`=#{endTime},`periodType`=#{periodType} where `id`=#{id}")
	public void update(MedicationTime medicationTime);

	@Delete("delete from `forest_medication_time` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_medication_time` limit #{start},#{count}")
	public List<MedicationTime> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_medication_time`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_medication_time`")
	public List<MedicationTime> listAll();
}