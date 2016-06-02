package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.personalcenter.model.MySignInStatistics;

public interface MySignInStatisticsMapper {

	@Insert("insert into `personalcenter_my_sign_in_statistics`(`id`,`userId`,`total`,`maxSignIn`,`currentSignIn`, lastSignInDay, firstSignInDay, firstIntegral)"
			+ " values(#{id},#{userId},#{total},#{maxSignIn},#{currentSignIn},#{lastSignInDay},#{firstSignInDay},#{firstIntegral})")
	public void insert(MySignInStatistics mySignInStatistics);

	@Select("select * from `personalcenter_my_sign_in_statistics` where `id`=#{id}")
	public MySignInStatistics get(Long id);

	@Update("update `personalcenter_my_sign_in_statistics` set `userId`=#{userId},`total`=#{total},`maxSignIn`=#{maxSignIn},`currentSignIn`=#{currentSignIn},lastSignInDay=#{lastSignInDay},firstSignInDay=#{firstSignInDay},firstIntegral=#{firstIntegral} where `id`=#{id}")
	public void update(MySignInStatistics mySignInStatistics);

	@Delete("delete from `personalcenter_my_sign_in_statistics` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_my_sign_in_statistics` limit #{start},#{count}")
	public List<MySignInStatistics> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_my_sign_in_statistics`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_my_sign_in_statistics`")
	public List<MySignInStatistics> listAll();
}