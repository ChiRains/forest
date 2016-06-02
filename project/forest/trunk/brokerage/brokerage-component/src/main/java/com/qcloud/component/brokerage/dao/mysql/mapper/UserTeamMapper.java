package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.brokerage.model.UserTeam;

public interface UserTeamMapper {

	@Insert("insert into `brokerage_user_team`(`id`,`userId`,`leader`)"
			+ " values(#{id},#{userId},#{leader})")
	public void insert(UserTeam userTeam);

	@Select("select * from `brokerage_user_team` where `id`=#{id}")
	public UserTeam get(Long id);

	@Update("update `brokerage_user_team` set `userId`=#{userId},`leader`=#{leader} where `id`=#{id}")
	public void update(UserTeam userTeam);

	@Delete("delete from `brokerage_user_team` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_user_team` limit #{start},#{count}")
	public List<UserTeam> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_user_team`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_user_team`")
	public List<UserTeam> listAll();
}