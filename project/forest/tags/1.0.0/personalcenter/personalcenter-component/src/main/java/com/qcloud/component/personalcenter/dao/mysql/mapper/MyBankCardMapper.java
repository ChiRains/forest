package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;

public interface MyBankCardMapper {

	@Insert("insert into `personalcenter_my_bank_card`(`id`,`userId`,`bank`,`cardholder`,`card`,`time`,`mobile`)"
			+ " values(#{id},#{userId},#{bank},#{cardholder},#{card},#{time},#{mobile})")
	public void insert(MyBankCard myBankCard);

	@Select("select * from `personalcenter_my_bank_card` where `id`=#{id}")
	public MyBankCard get(Long id);

	@Update("update `personalcenter_my_bank_card` set `userId`=#{userId},`bank`=#{bank},`cardholder`=#{cardholder},`card`=#{card},`time`=#{time} ,`mobile`=#{mobile}  where `id`=#{id}")
	public void update(MyBankCard myBankCard);

	@Delete("delete from `personalcenter_my_bank_card` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_my_bank_card` limit #{start},#{count}")
	public List<MyBankCard> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_my_bank_card`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_my_bank_card`")
	public List<MyBankCard> listAll();
}