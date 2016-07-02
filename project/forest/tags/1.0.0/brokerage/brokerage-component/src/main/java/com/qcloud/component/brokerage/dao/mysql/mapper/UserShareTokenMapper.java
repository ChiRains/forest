package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.brokerage.model.UserShareToken;

public interface UserShareTokenMapper {

	@Insert("insert into `brokerage_user_share_token`(`id`,`userId`,`token`)"
			+ " values(#{id},#{userId},#{token})")
	public void insert(UserShareToken userShareToken);

	@Select("select * from `brokerage_user_share_token` where `id`=#{id}")
	public UserShareToken get(Long id);

	@Update("update `brokerage_user_share_token` set `userId`=#{userId},`token`=#{token} where `id`=#{id}")
	public void update(UserShareToken userShareToken);

	@Delete("delete from `brokerage_user_share_token` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_user_share_token` limit #{start},#{count}")
	public List<UserShareToken> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_user_share_token`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_user_share_token`")
	public List<UserShareToken> listAll();
}