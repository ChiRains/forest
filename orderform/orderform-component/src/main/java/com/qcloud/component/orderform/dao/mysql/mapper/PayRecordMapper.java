//package com.qcloud.component.orderform.dao.mysql.mapper;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import com.qcloud.component.orderform.model.PayRecord;
//import com.qcloud.component.orderform.model.query.PayRecordQuery;
//
//public interface PayRecordMapper {
//
//	@Insert("insert into `orderform_pay_record`(`id`,`orderNumber`,`orderId`,`orderDate`,`tradeId`,`tradeTyped`,`sum`,`time`)"
//			+ " values(#{id},#{orderNumber},#{orderId},#{orderDate},#{tradeId},#{tradeTyped},#{sum},#{time})")
//	public void insert(PayRecord payRecord);
//
//	@Select("select * from `orderform_pay_record` where `id`=#{id}")
//	public PayRecord get(Long id);
//
//	@Update("update `orderform_pay_record` set `orderNumber`=#{orderNumber},`orderId`=#{orderId},`orderDate`=#{orderDate},`tradeId`=#{tradeId},`tradeTyped`=#{tradeTyped},`sum`=#{sum},`time`=#{time} where `id`=#{id}")
//	public void update(PayRecord payRecord);
//
//	@Delete("delete from `orderform_pay_record` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `orderform_pay_record` limit #{start},#{count}")
//	public List<PayRecord> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `orderform_pay_record`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `orderform_pay_record`")
//	public List<PayRecord> listAll();
//}