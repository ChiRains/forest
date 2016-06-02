package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;

public interface MembershipCardWarehouseMapper {

	@Insert("insert into `personalcenter_membership_card_warehouse`(`id`,`cardNumber`,`state`)"
			+ " values(#{id},#{cardNumber},#{state})")
	public void insert(MembershipCardWarehouse membershipCardWarehouse);

	@Select("select * from `personalcenter_membership_card_warehouse` where `id`=#{id}")
	public MembershipCardWarehouse get(Long id);

	@Update("update `personalcenter_membership_card_warehouse` set `cardNumber`=#{cardNumber},`state`=#{state} where `id`=#{id}")
	public void update(MembershipCardWarehouse membershipCardWarehouse);

	@Delete("delete from `personalcenter_membership_card_warehouse` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_membership_card_warehouse` limit #{start},#{count}")
	public List<MembershipCardWarehouse> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_membership_card_warehouse`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_membership_card_warehouse`")
	public List<MembershipCardWarehouse> listAll();
}