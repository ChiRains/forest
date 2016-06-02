package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;

public interface UpgradeGiftMapper {

	@Insert("insert into `brokerage_upgrade_gift`(`id`,`gradeId`,`couponId`,`number`,`limitTime`)"
			+ " values(#{id},#{gradeId},#{couponId},#{number},#{limitTime})")
	public void insert(UpgradeGift upgradeGift);

	@Select("select * from `brokerage_upgrade_gift` where `id`=#{id}")
	public UpgradeGift get(Long id);

	@Update("update `brokerage_upgrade_gift` set `gradeId`=#{gradeId},`couponId`=#{couponId},`number`=#{number},`limitTime`=#{limitTime} where `id`=#{id}")
	public void update(UpgradeGift upgradeGift);

	@Delete("delete from `brokerage_upgrade_gift` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_upgrade_gift` limit #{start},#{count}")
	public List<UpgradeGift> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_upgrade_gift`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_upgrade_gift`")
	public List<UpgradeGift> listAll();
}