package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.query.MedicationQuery;

public interface MedicationMapper {

	@Insert("insert into `forest_medication`(`id`,`theme`,`image`,`medicine`,`objectName`,`dosage`,`unit`,`desc`,`enable`,`userId`,`recordTime`,`periodTimes`,`useTimes`)"
			+ " values(#{id},#{theme},#{image},#{medicine},#{objectName},#{dosage},#{unit},#{desc},#{enable},#{userId},#{recordTime},#{periodTimes},#{useTimes})")
	public void insert(Medication medication);

	@Select("select * from `forest_medication` where `id`=#{id}")
	public Medication get(Long id);

	@Update("update `forest_medication` set `theme`=#{theme},`image`=#{image},`medicine`=#{medicine},`objectName`=#{objectName},`dosage`=#{dosage},`unit`=#{unit},`desc`=#{desc},`enable`=#{enable},`userId`=#{userId},`recordTime`=#{recordTime},`periodTimes`=#{periodTimes},`useTimes`=#{useTimes} where `id`=#{id}")
	public void update(Medication medication);

	@Delete("delete from `forest_medication` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_medication` limit #{start},#{count}")
	public List<Medication> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_medication`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_medication`")
	public List<Medication> listAll();
}