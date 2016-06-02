package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.query.MySignInRecordQuery;

public interface MySignInRecordMapper {

	@Insert("insert into `personalcenter_my_sign_in_record`(`id`,`userId`,`integral`,`signtime`)"
			+ " values(#{id},#{userId},#{integral},#{signtime})")
	public void insert(MySignInRecord mySignInRecord);

	@Select("select * from `personalcenter_my_sign_in_record` where `id`=#{id}")
	public MySignInRecord get(Long id);

	@Update("update `personalcenter_my_sign_in_record` set `userId`=#{userId},`integral`=#{integral},`signtime`=#{signtime} where `id`=#{id}")
	public void update(MySignInRecord mySignInRecord);

	@Delete("delete from `personalcenter_my_sign_in_record` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_my_sign_in_record` limit #{start},#{count}")
	public List<MySignInRecord> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_my_sign_in_record`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_my_sign_in_record`")
	public List<MySignInRecord> listAll();
}