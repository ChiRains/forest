package com.qcloud.component.marketing.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;

public interface SlideMapper {

	@Insert("insert into `marketing_slide`(`id`,`clickUrl`,`image`,`sence`,`orderNum`,`desc`)"
			+ " values(#{id},#{clickUrl},#{image},#{sence},#{orderNum},#{desc})")
	public void insert(Slide slide);

	@Select("select * from `marketing_slide` where `id`=#{id}")
	public Slide get(Long id);

	@Update("update `marketing_slide` set `clickUrl`=#{clickUrl},`image`=#{image},`sence`=#{sence},`orderNum`=#{orderNum},`desc`=#{desc} where `id`=#{id}")
	public void update(Slide slide);

	@Delete("delete from `marketing_slide` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `marketing_slide` limit #{start},#{count}")
	public List<Slide> list4page(Map<String,Object> param);

	@Select("select count(*) from `marketing_slide`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `marketing_slide`")
	public List<Slide> listAll();
}