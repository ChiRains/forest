package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.query.MySignInDayQuery;

public interface MySignInDayMapper {

	@Insert("insert into `personalcenter_my_sign_in_day`(`id`,`userId`,`year`,`month`,`day`)"
			+ " values(#{id},#{userId},#{year},#{month},#{day})")
	public void insert(MySignInDay mySignInDay);

	@Select("select * from `personalcenter_my_sign_in_day` where `id`=#{id}")
	public MySignInDay get(Long id);

	@Update("update `personalcenter_my_sign_in_day` set `userId`=#{userId},`year`=#{year},`month`=#{month},`day`=#{day} where `id`=#{id}")
	public void update(MySignInDay mySignInDay);

	@Delete("delete from `personalcenter_my_sign_in_day` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_my_sign_in_day` limit #{start},#{count}")
	public List<MySignInDay> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_my_sign_in_day`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_my_sign_in_day`")
	public List<MySignInDay> listAll();
}