package com.qcloud.component.seckill.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;

public interface ScreeningsSlideMapper {

	@Insert("insert into `seckill_screenings_slide`(`id`,`screeningsId`,`clickUrl`,`image`,`orderNum`)"
			+ " values(#{id},#{screeningsId},#{clickUrl},#{image},#{orderNum})")
	public void insert(ScreeningsSlide screeningsSlide);

	@Select("select * from `seckill_screenings_slide` where `id`=#{id}")
	public ScreeningsSlide get(Long id);

	@Update("update `seckill_screenings_slide` set `screeningsId`=#{screeningsId},`clickUrl`=#{clickUrl},`image`=#{image},`orderNum`=#{orderNum} where `id`=#{id}")
	public void update(ScreeningsSlide screeningsSlide);

	@Delete("delete from `seckill_screenings_slide` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `seckill_screenings_slide` limit #{start},#{count}")
	public List<ScreeningsSlide> list4page(Map<String,Object> param);

	@Select("select count(*) from `seckill_screenings_slide`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `seckill_screenings_slide`")
	public List<ScreeningsSlide> listAll();
}