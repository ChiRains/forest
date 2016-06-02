package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.query.UserQuery;

public interface UserMapper {

	@Insert("insert into `personalcenter_user`(`id`,`membershipCard`,`mobile`,`email`,`accountGroup`,`nickname`,`name`,`headImage`,`state`,`type`,`sex`,`gradeId`,`birthYear`,`birthMonth`,`birthDay`,`province`,`city`,`district`,`registTime`,`address`)"
			+ " values(#{id},#{membershipCard},#{mobile},#{email},#{accountGroup},#{nickname},#{name},#{headImage},#{state},#{type},#{sex},#{gradeId},#{birthYear},#{birthMonth},#{birthDay},#{province},#{city},#{district},#{registTime},#{address})")
	public void insert(User user);

	@Select("select * from `personalcenter_user` where `id`=#{id}")
	public User get(Long id);

	@Update("update `personalcenter_user` set `membershipCard`=#{membershipCard},`mobile`=#{mobile},`email`=#{email},`accountGroup`=#{accountGroup},`nickname`=#{nickname},`name`=#{name},`headImage`=#{headImage},`state`=#{state},`type`=#{type},`sex`=#{sex},`gradeId`=#{gradeId},`birthYear`=#{birthYear},`birthMonth`=#{birthMonth},`birthDay`=#{birthDay},`province`=#{province},`city`=#{city},`district`=#{district},`registTime`=#{registTime},`address`=#{address}  where `id`=#{id}")
	public void update(User user);

	@Delete("delete from `personalcenter_user` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_user` limit #{start},#{count}")
	public List<User> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_user`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_user`")
	public List<User> listAll();
}