package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.query.ShareGiftQuery;

public interface ShareGiftMapper {

	@Insert("insert into `forest_share_gift`(`id`,`userId`,`code`)"
			+ " values(#{id},#{userId},#{code})")
	public void insert(ShareGift shareGift);

	@Select("select * from `forest_share_gift` where `id`=#{id}")
	public ShareGift get(Long id);

	@Update("update `forest_share_gift` set `userId`=#{userId},`code`=#{code} where `id`=#{id}")
	public void update(ShareGift shareGift);

	@Delete("delete from `forest_share_gift` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_share_gift` limit #{start},#{count}")
	public List<ShareGift> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_share_gift`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_share_gift`")
	public List<ShareGift> listAll();
}