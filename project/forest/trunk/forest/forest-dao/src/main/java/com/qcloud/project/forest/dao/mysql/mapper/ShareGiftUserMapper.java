package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.model.query.ShareGiftUserQuery;

public interface ShareGiftUserMapper {

	@Insert("insert into `forest_share_gift_user`(`id`,`userId`,`code`,`state`)"
			+ " values(#{id},#{userId},#{code},#{state})")
	public void insert(ShareGiftUser shareGiftUser);

	@Select("select * from `forest_share_gift_user` where `id`=#{id}")
	public ShareGiftUser get(Long id);

	@Update("update `forest_share_gift_user` set `userId`=#{userId},`code`=#{code},`state`=#{state} where `id`=#{id}")
	public void update(ShareGiftUser shareGiftUser);

	@Delete("delete from `forest_share_gift_user` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_share_gift_user` limit #{start},#{count}")
	public List<ShareGiftUser> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_share_gift_user`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_share_gift_user`")
	public List<ShareGiftUser> listAll();
}