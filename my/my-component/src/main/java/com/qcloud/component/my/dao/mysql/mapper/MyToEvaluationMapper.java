package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;

public interface MyToEvaluationMapper {

	@Insert("insert into `my_my_to_evaluation`(`id`,`userId`,`unifiedMerchandiseId`,`merchandiseId`,`name`,`image`,`discount`,`merchantId`,`orderId`,`subOrderId`,`orderItemId`,`orderDate`,`orderNumber`,`signDate`,`orderItemDetailId`,`specifications`)"
			+ " values(#{id},#{userId},#{unifiedMerchandiseId},#{merchandiseId},#{name},#{image},#{discount},#{merchantId},#{orderId},#{subOrderId},#{orderItemId},#{orderDate},#{orderNumber},#{signDate},#{orderItemDetailId},#{specifications})")
	public void insert(MyToEvaluation myToEvaluation);

	@Select("select * from `my_my_to_evaluation` where `id`=#{id}")
	public MyToEvaluation get(Long id);

	@Update("update `my_my_to_evaluation` set `userId`=#{userId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`merchandiseId`=#{merchandiseId},`name`=#{name},`image`=#{image},`discount`=#{discount},`merchantId`=#{merchantId},`orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`orderDate`=#{orderDate},`orderNumber`=#{orderNumber},`signDate`=#{signDate},`orderItemDetailId`=#{orderItemDetailId},`specifications`=#{specifications} where `id`=#{id}")
	public void update(MyToEvaluation myToEvaluation);

	@Delete("delete from `my_my_to_evaluation` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_my_to_evaluation` limit #{start},#{count}")
	public List<MyToEvaluation> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_my_to_evaluation`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_my_to_evaluation`")
	public List<MyToEvaluation> listAll();
}