package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.goods.model.MerchandiseEvaluation;

public interface MerchandiseEvaluationMapper {

	@Insert("insert into `${table_name}`(`id`,`merchandiseId`,`content`,`star`,`time`,`status`,`specifications`,`userId`,`anonymous`,`images`,`addContent`,`replyContent`)"
			+ " values(#{id},#{merchandiseId},#{content},#{star},#{time},#{status},#{specifications},#{userId},#{anonymous},#{images},#{addContent},#{replyContent})")
	public void insert(MerchandiseEvaluation merchandiseEvaluation);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MerchandiseEvaluation get(Long id);

	@Update("update `${table_name}` set `merchandiseId`=#{merchandiseId},`content`=#{content},`star`=#{star},`time`=#{time},`status`=#{status},`specifications`=#{specifications},`userId`=#{userId},`anonymous`=#{anonymous},`images`=#{images},`addContent`=#{addContent},`replyContent`=#{replyContent} where `id`=#{id}")
	public void update(MerchandiseEvaluation merchandiseEvaluation);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MerchandiseEvaluation> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MerchandiseEvaluation> listAll();
}