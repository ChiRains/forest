package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;

public interface DistributeMembershipCardMapper {

	@Insert("insert into `sellercenter_distribute_membership_card`(`id`,`merchantId`,`merchantCode`,`merchantName`,`memberId`,`cardNumber`,`state`,`time`)"
			+ " values(#{id},#{merchantId},#{merchantCode},#{merchantName},#{memberId},#{cardNumber},#{state},#{time})")
	public void insert(DistributeMembershipCard distributeMembershipCard);

	@Select("select * from `sellercenter_distribute_membership_card` where `id`=#{id}")
	public DistributeMembershipCard get(Long id);

	@Update("update `sellercenter_distribute_membership_card` set `merchantId`=#{merchantId},`merchantCode`=#{merchantCode},`merchantName`=#{merchantName},`memberId`=#{memberId},`cardNumber`=#{cardNumber},`state`=#{state},`time`=#{time} where `id`=#{id}")
	public void update(DistributeMembershipCard distributeMembershipCard);

	@Delete("delete from `sellercenter_distribute_membership_card` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_distribute_membership_card` limit #{start},#{count}")
	public List<DistributeMembershipCard> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_distribute_membership_card`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_distribute_membership_card`")
	public List<DistributeMembershipCard> listAll();
}