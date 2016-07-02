package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.query.ConsigneeQuery;

public interface ConsigneeMapper {

	@Insert("insert into `my_consignee`(`id`,`userId`,`name`,`province`,`city`,`district`,`address`,`mobile`,`email`,`zipCode`,`acquiesce`)"
			+ " values(#{id},#{userId},#{name},#{province},#{city},#{district},#{address},#{mobile},#{email},#{zipCode},#{acquiesce})")
	public void insert(Consignee consignee);

	@Select("select * from `my_consignee` where `id`=#{id}")
	public Consignee get(Long id);

	@Update("update `my_consignee` set `userId`=#{userId},`name`=#{name},`province`=#{province},`city`=#{city},`district`=#{district},`address`=#{address},`mobile`=#{mobile},`email`=#{email},`zipCode`=#{zipCode},`acquiesce`=#{acquiesce} where `id`=#{id}")
	public void update(Consignee consignee);

	@Delete("delete from `my_consignee` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_consignee` limit #{start},#{count}")
	public List<Consignee> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_consignee`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_consignee`")
	public List<Consignee> listAll();
}