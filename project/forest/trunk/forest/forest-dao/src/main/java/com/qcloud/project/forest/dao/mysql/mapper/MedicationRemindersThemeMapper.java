package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.model.query.MedicationRemindersThemeQuery;

public interface MedicationRemindersThemeMapper {

	@Insert("insert into `forest_medication_reminders_theme`(`id`,`name`,`image`,`userId`,`enable`,`recordTime`)"
			+ " values(#{id},#{name},#{image},#{userId},#{enable},#{recordTime})")
	public void insert(MedicationRemindersTheme medicationRemindersTheme);

	@Select("select * from `forest_medication_reminders_theme` where `id`=#{id}")
	public MedicationRemindersTheme get(Long id);

	@Update("update `forest_medication_reminders_theme` set `name`=#{name},`image`=#{image},`userId`=#{userId},`enable`=#{enable},`recordTime`=#{recordTime} where `id`=#{id}")
	public void update(MedicationRemindersTheme medicationRemindersTheme);

	@Delete("delete from `forest_medication_reminders_theme` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_medication_reminders_theme` limit #{start},#{count}")
	public List<MedicationRemindersTheme> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_medication_reminders_theme`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_medication_reminders_theme`")
	public List<MedicationRemindersTheme> listAll();
}