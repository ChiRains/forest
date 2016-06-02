package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;

public interface MySignInMonthMapper {

	@Insert("insert into `personalcenter_my_sign_in_month`(`id`,`userId`,`year`,`month`,`record`)"
			+ " values(#{id},#{userId},#{year},#{month},#{record})")
	public void insert(MySignInMonth mySignInMonth);

	@Select("select * from `personalcenter_my_sign_in_month` where `id`=#{id}")
	public MySignInMonth get(Long id);

	@Update("update `personalcenter_my_sign_in_month` set `userId`=#{userId},`year`=#{year},`month`=#{month},`record`=#{record} where `id`=#{id}")
	public void update(MySignInMonth mySignInMonth);

	@Delete("delete from `personalcenter_my_sign_in_month` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_my_sign_in_month` limit #{start},#{count}")
	public List<MySignInMonth> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_my_sign_in_month`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_my_sign_in_month`")
	public List<MySignInMonth> listAll();
}