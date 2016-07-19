package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.model.query.MyToAppendEvaluationQuery;

public interface MyToAppendEvaluationMapper {

	@Insert("insert into `my_my_to_append_evaluation`(`id`,`userId`,`unifiedMerchandiseId`,`merchandiseId`,`name`,`image`,`discount`,`merchantId`,`orderId`,`subOrderId`,`orderItemId`,`orderDate`,`orderNumber`,`signDate`,`orderItemDetailId`,`specifications`,`evaluationId`)"
			+ " values(#{id},#{userId},#{unifiedMerchandiseId},#{merchandiseId},#{name},#{image},#{discount},#{merchantId},#{orderId},#{subOrderId},#{orderItemId},#{orderDate},#{orderNumber},#{signDate},#{orderItemDetailId},#{specifications},#{evaluationId})")
	public void insert(MyToAppendEvaluation myToAppendEvaluation);

	@Select("select * from `my_my_to_append_evaluation` where `id`=#{id}")
	public MyToAppendEvaluation get(Long id);

	@Update("update `my_my_to_append_evaluation` set `userId`=#{userId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`merchandiseId`=#{merchandiseId},`name`=#{name},`image`=#{image},`discount`=#{discount},`merchantId`=#{merchantId},`orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`orderDate`=#{orderDate},`orderNumber`=#{orderNumber},`signDate`=#{signDate},`orderItemDetailId`=#{orderItemDetailId},`specifications`=#{specifications},`evaluationId`=#{evaluationId} where `id`=#{id}")
	public void update(MyToAppendEvaluation myToAppendEvaluation);

	@Delete("delete from `my_my_to_append_evaluation` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_my_to_append_evaluation` limit #{start},#{count}")
	public List<MyToAppendEvaluation> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_my_to_append_evaluation`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_my_to_append_evaluation`")
	public List<MyToAppendEvaluation> listAll();
}