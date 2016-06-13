package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.query.MedicationRemindersTimeQuery;

public interface MedicationRemindersTimeMapper {

	@Insert("insert into `forest_medication_reminders_time`(`id`,`reminderId`,`excuteTime`)"
			+ " values(#{id},#{reminderId},#{excuteTime})")
	public void insert(MedicationRemindersTime medicationRemindersTime);

	@Select("select * from `forest_medication_reminders_time` where `id`=#{id}")
	public MedicationRemindersTime get(Long id);

	@Update("update `forest_medication_reminders_time` set `reminderId`=#{reminderId},`excuteTime`=#{excuteTime} where `id`=#{id}")
	public void update(MedicationRemindersTime medicationRemindersTime);

	@Delete("delete from `forest_medication_reminders_time` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_medication_reminders_time` limit #{start},#{count}")
	public List<MedicationRemindersTime> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_medication_reminders_time`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_medication_reminders_time`")
	public List<MedicationRemindersTime> listAll();
}