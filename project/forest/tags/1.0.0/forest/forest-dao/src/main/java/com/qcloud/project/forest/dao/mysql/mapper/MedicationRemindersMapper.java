package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;

public interface MedicationRemindersMapper {

	@Insert("insert into `forest_medication_reminders`(`id`,`userId`,`themeId`,`medicineName`,`medicineId`,`medicineDosage`,`medicineUnit`,`periodTimes`,`useTimes`,`desc`,`recordTime`)"
			+ " values(#{id},#{userId},#{themeId},#{medicineName},#{medicineId},#{medicineDosage},#{medicineUnit},#{periodTimes},#{useTimes},#{desc},#{recordTime})")
	public void insert(MedicationReminders medicationReminders);

	@Select("select * from `forest_medication_reminders` where `id`=#{id}")
	public MedicationReminders get(Long id);

	@Update("update `forest_medication_reminders` set `userId`=#{userId},`themeId`=#{themeId},`medicineName`=#{medicineName},`medicineId`=#{medicineId},`medicineDosage`=#{medicineDosage},`medicineUnit`=#{medicineUnit},`periodTimes`=#{periodTimes},`useTimes`=#{useTimes},`desc`=#{desc},`recordTime`=#{recordTime} where `id`=#{id}")
	public void update(MedicationReminders medicationReminders);

	@Delete("delete from `forest_medication_reminders` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_medication_reminders` limit #{start},#{count}")
	public List<MedicationReminders> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_medication_reminders`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_medication_reminders`")
	public List<MedicationReminders> listAll();
}