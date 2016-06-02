package com.qcloud.component.seckill.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;

public interface ScreeningsMapper {

	@Insert("insert into `seckill_screenings`(`id`,`beginTime`,`endTime`,`enable`)"
			+ " values(#{id},#{beginTime},#{endTime},#{enable})")
	public void insert(Screenings screenings);

	@Select("select * from `seckill_screenings` where `id`=#{id}")
	public Screenings get(Long id);

	@Update("update `seckill_screenings` set `beginTime`=#{beginTime},`endTime`=#{endTime},`enable`=#{enable} where `id`=#{id}")
	public void update(Screenings screenings);

	@Delete("delete from `seckill_screenings` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `seckill_screenings` limit #{start},#{count}")
	public List<Screenings> list4page(Map<String,Object> param);

	@Select("select count(*) from `seckill_screenings`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `seckill_screenings`")
	public List<Screenings> listAll();
}