package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.my.model.MyOrderForm;

public interface MyOrderFormMapper {

	@Insert("insert into `${table_name}`(`id`,`userId`,`orderId`,`subOrderId`,`time`,`lastUpdateTime`,`state`)"
			+ " values(#{id},#{userId},#{orderId},#{subOrderId},#{time},#{lastUpdateTime},#{state})")
	public void insert(MyOrderForm myOrderForm);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MyOrderForm get(Long id);

	@Update("update `${table_name}` set `userId`=#{userId},`orderId`=#{orderId},`subOrderId`=#{subOrderId}, `time`=#{time},lastUpdateTime=#{lastUpdateTime},`state`=#{state} where `id`=#{id}")
	public void update(MyOrderForm myOrderForm);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MyOrderForm> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MyOrderForm> listAll();
}