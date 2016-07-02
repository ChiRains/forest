package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.query.RecordStateTimeQuery;

public interface RecordStateTimeMapper {

	@Insert("insert into `orderform_record_state_time`(`id`,`orderId`,`orderDate`,`dataId`,`dataIdType`,`state`,`time`)"
			+ " values(#{id},#{orderId},#{orderDate},#{dataId},#{dataIdType},#{state},#{time})")
	public void insert(RecordStateTime recordStateTime);

	@Select("select * from `orderform_record_state_time` where `id`=#{id}")
	public RecordStateTime get(Long id);

	@Update("update `orderform_record_state_time` set `orderId`=#{orderId},`orderDate`=#{orderDate},`dataId`=#{dataId},`dataIdType`=#{dataIdType},`state`=#{state},`time`=#{time} where `id`=#{id}")
	public void update(RecordStateTime recordStateTime);

	@Delete("delete from `orderform_record_state_time` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_record_state_time` limit #{start},#{count}")
	public List<RecordStateTime> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_record_state_time`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_record_state_time`")
	public List<RecordStateTime> listAll();
}