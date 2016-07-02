package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.brokerage.model.UserRelationship;

public interface UserRelationshipMapper {

	@Insert("insert into `brokerage_user_relationship`(`id`,`userId`,`recommedId`,`allocation`,`time`)"
			+ " values(#{id},#{userId},#{recommedId},#{allocation},#{time})")
	public void insert(UserRelationship userRelationship);

	@Select("select * from `brokerage_user_relationship` where `id`=#{id}")
	public UserRelationship get(Long id);

	@Update("update `brokerage_user_relationship` set `userId`=#{userId},`recommedId`=#{recommedId},`allocation`=#{allocation},`time`=#{time} where `id`=#{id}")
	public void update(UserRelationship userRelationship);

	@Delete("delete from `brokerage_user_relationship` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_user_relationship` limit #{start},#{count}")
	public List<UserRelationship> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_user_relationship`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_user_relationship`")
	public List<UserRelationship> listAll();
}