package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.query.UpgradeOrderQuery;

public interface UpgradeOrderMapper {

	@Insert("insert into `brokerage_upgrade_order`(`id`,`orderNumber`,`userId`,`time`,`deadlinePayTime`,`originalGradeId`,`upgradeGradeId`,`cash`,`paymentMode`,`state`)"
			+ " values(#{id},#{orderNumber},#{userId},#{time},#{deadlinePayTime},#{originalGradeId},#{upgradeGradeId},#{cash},#{paymentMode},#{state})")
	public void insert(UpgradeOrder upgradeOrder);

	@Select("select * from `brokerage_upgrade_order` where `id`=#{id}")
	public UpgradeOrder get(Long id);

	@Update("update `brokerage_upgrade_order` set `orderNumber`=#{orderNumber},`userId`=#{userId},`time`=#{time},`deadlinePayTime`=#{deadlinePayTime},`originalGradeId`=#{originalGradeId},`upgradeGradeId`=#{upgradeGradeId},`cash`=#{cash},`paymentMode`=#{paymentMode},`state`=#{state} where `id`=#{id}")
	public void update(UpgradeOrder upgradeOrder);

	@Delete("delete from `brokerage_upgrade_order` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_upgrade_order` limit #{start},#{count}")
	public List<UpgradeOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_upgrade_order`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_upgrade_order`")
	public List<UpgradeOrder> listAll();
}