package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.model.query.RemindRecordQuery;

public interface RemindRecordMapper {

	@Insert("insert into `orderform_remind_record`(`id`,`orderId`,`subOrderId`,`orderDate`,`time`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{orderDate},#{time})")
	public void insert(RemindRecord remindRecord);

	@Select("select * from `orderform_remind_record` where `id`=#{id}")
	public RemindRecord get(Long id);

	@Update("update `orderform_remind_record` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderDate`=#{orderDate},`time`=#{time} where `id`=#{id}")
	public void update(RemindRecord remindRecord);

	@Delete("delete from `orderform_remind_record` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_remind_record` limit #{start},#{count}")
	public List<RemindRecord> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_remind_record`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_remind_record`")
	public List<RemindRecord> listAll();
}