package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;

public interface RefundOrderMapper {

	@Insert("insert into `orderform_refund_order`(`id`,`orderId`,`orderDate`,`orderNumber`,`subOrderId`,`merchantId`,`storeId`,`sum`,`state`,`userId`,`time`,`refundNumber`,`explain`,`reason`,`lastUpdateTime`,`stateValidTime`)"
			+ " values(#{id},#{orderId},#{orderDate},#{orderNumber},#{subOrderId},#{merchantId},#{storeId},#{sum},#{state},#{userId},#{time},#{refundNumber},#{explain},#{reason},#{lastUpdateTime},#{stateValidTime})")
	public void insert(RefundOrder refundOrder);

	@Select("select * from `orderform_refund_order` where `id`=#{id}")
	public RefundOrder get(Long id);

	@Update("update `orderform_refund_order` set `orderId`=#{orderId},`orderDate`=#{orderDate},`orderNumber`=#{orderNumber},`subOrderId`=#{subOrderId},`merchantId`=#{merchantId},`storeId`=#{storeId},`sum`=#{sum},`state`=#{state},`userId`=#{userId},`time`=#{time},`refundNumber`=#{refundNumber},`explain`=#{explain},`reason`=#{reason},`lastUpdateTime`=#{lastUpdateTime},`stateValidTime`=#{stateValidTime} where `id`=#{id}")
	public void update(RefundOrder refundOrder);

	@Delete("delete from `orderform_refund_order` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_refund_order` limit #{start},#{count}")
	public List<RefundOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_refund_order`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_refund_order`")
	public List<RefundOrder> listAll();
}