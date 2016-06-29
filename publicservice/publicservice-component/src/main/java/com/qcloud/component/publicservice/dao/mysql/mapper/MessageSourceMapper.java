package com.qcloud.component.publicservice.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;

public interface MessageSourceMapper {

	@Insert("insert into `publicservice_message_source`(`id`,`title`,`content`,`classifyId`,`merchantId`,`time`,`type`)"
			+ " values(#{id},#{title},#{content},#{classifyId},#{merchantId},#{time},#{type})")
	public void insert(MessageSource messageSource);

	@Select("select * from `publicservice_message_source` where `id`=#{id}")
	public MessageSource get(Long id);

	@Update("update `publicservice_message_source` set `title`=#{title},`content`=#{content},`classifyId`=#{classifyId},`merchantId`=#{merchantId},`time`=#{time},`type`=#{type} where `id`=#{id}")
	public void update(MessageSource messageSource);

	@Delete("delete from `publicservice_message_source` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `publicservice_message_source` limit #{start},#{count}")
	public List<MessageSource> list4page(Map<String,Object> param);

	@Select("select count(*) from `publicservice_message_source`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `publicservice_message_source`")
	public List<MessageSource> listAll();
}