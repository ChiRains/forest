package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.my.model.MyEvaluation;

public interface MyEvaluationMapper {

	@Insert("insert into `${table_name}`(`id`,`evaluationId`,`merchandiseId`,`userId`,`orderItemDetailId`,`orderTime`)"
			+ " values(#{id},#{evaluationId},#{merchandiseId},#{userId},#{orderItemDetailId},#{orderTime})")
	public void insert(MyEvaluation myEvaluation);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MyEvaluation get(Long id);

	@Update("update `${table_name}` set `evaluationId`=#{evaluationId},`merchandiseId`=#{merchandiseId},,`userId`=#{userId},`orderItemDetailId`=#{orderItemDetailId},`orderTime`=#{orderTime} where `id`=#{id}")
	public void update(MyEvaluation myEvaluation);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MyEvaluation> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MyEvaluation> listAll();
}