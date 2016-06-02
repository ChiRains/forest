package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;

public interface MyCommissionWithdrawCashMapper {

	@Insert("insert into `personalcenter_my_commission_withdraw_cash`(`id`,`wealthId`,`wealthDetailId`,`userId`,`commissionCash`,`cardholder`,`bank`,`card`,`time`,`completeTime`,`state`)"
			+ " values(#{id},#{wealthId},#{wealthDetailId},#{userId},#{commissionCash},#{cardholder},#{bank},#{card},#{time},#{completeTime},#{state})")
	public void insert(MyCommissionWithdrawCash myCommissionWithdrawCash);

	@Select("select * from `personalcenter_my_commission_withdraw_cash` where `id`=#{id}")
	public MyCommissionWithdrawCash get(Long id);

	@Update("update `personalcenter_my_commission_withdraw_cash` set `wealthId`=#{wealthId},`wealthDetailId`=#{wealthDetailId},`userId`=#{userId},`commissionCash`=#{commissionCash},`cardholder`=#{cardholder},`bank`=#{bank},`card`=#{card},`time`=#{time},`completeTime`=#{completeTime},`state`=#{state} where `id`=#{id}")
	public void update(MyCommissionWithdrawCash myCommissionWithdrawCash);

	@Delete("delete from `personalcenter_my_commission_withdraw_cash` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_my_commission_withdraw_cash` limit #{start},#{count}")
	public List<MyCommissionWithdrawCash> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_my_commission_withdraw_cash`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_my_commission_withdraw_cash`")
	public List<MyCommissionWithdrawCash> listAll();
}