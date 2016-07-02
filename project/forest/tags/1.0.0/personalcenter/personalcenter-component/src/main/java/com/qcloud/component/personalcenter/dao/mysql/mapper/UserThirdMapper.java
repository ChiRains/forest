package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.query.UserThirdQuery;

public interface UserThirdMapper {

	@Insert("insert into `personalcenter_user_third`(`id`,`userId`,`thirdId`,`accountType`,`createTime`)"
			+ " values(#{id},#{userId},#{thirdId},#{accountType},#{createTime})")
	public void insert(UserThird userThird);

	@Select("select * from `personalcenter_user_third` where `id`=#{id}")
	public UserThird get(Long id);

	@Update("update `personalcenter_user_third` set `userId`=#{userId},`thirdId`=#{thirdId},`accountType`=#{accountType},`createTime`=#{createTime} where `id`=#{id}")
	public void update(UserThird userThird);

	@Delete("delete from `personalcenter_user_third` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_user_third` limit #{start},#{count}")
	public List<UserThird> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_user_third`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_user_third`")
	public List<UserThird> listAll();
}