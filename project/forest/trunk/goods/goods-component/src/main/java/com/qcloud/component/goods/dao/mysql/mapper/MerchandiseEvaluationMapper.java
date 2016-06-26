package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;

public interface MerchandiseEvaluationMapper {

	@Insert("insert into `goods_merchandise_evaluation`(`id`,`merchandiseId`,`content`,`star`,`time`,`status`,`specifications`,`userId`,`anonymous`)"
			+ " values(#{id},#{merchandiseId},#{content},#{star},#{time},#{status},#{specifications},#{userId},#{anonymous})")
	public void insert(MerchandiseEvaluation merchandiseEvaluation);

	@Select("select * from `goods_merchandise_evaluation` where `id`=#{id}")
	public MerchandiseEvaluation get(Long id);

	@Update("update `goods_merchandise_evaluation` set `merchandiseId`=#{merchandiseId},`content`=#{content},`star`=#{star},`time`=#{time},`status`=#{status},`specifications`=#{specifications},`userId`=#{userId},`anonymous`=#{anonymous} where `id`=#{id}")
	public void update(MerchandiseEvaluation merchandiseEvaluation);

	@Delete("delete from `goods_merchandise_evaluation` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_evaluation` limit #{start},#{count}")
	public List<MerchandiseEvaluation> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_evaluation`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_evaluation`")
	public List<MerchandiseEvaluation> listAll();
}