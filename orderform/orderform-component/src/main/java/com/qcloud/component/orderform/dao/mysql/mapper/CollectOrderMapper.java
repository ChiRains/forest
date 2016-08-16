package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;

public interface CollectOrderMapper {

	@Insert("insert into `${table_name}`(`id`,`orderNumber`,`userId`,`time`,`lastUpdateTime`,`sum`,`cash`,`discount`,`coupon`,`postage`,`integral`,`consumption`,`preferential`,`consignee`,`address`,`mobile`,`email`,`paymentMode`,`state`,`prestate`,`needInvoice`,`invoiceType`,`invoiceHead`,`invoiceContent`,`evaluation`,`afterSale`,`stateValidTime`,`province`,`city`,`district`)"
			+ " values(#{id},#{orderNumber},#{userId},#{time},#{lastUpdateTime},#{sum},#{cash},#{discount},#{coupon},#{postage},#{integral},#{consumption},#{preferential},#{consignee},#{address},#{mobile},#{email},#{paymentMode},#{state},#{prestate},#{needInvoice},#{invoiceType},#{invoiceHead},#{invoiceContent},#{evaluation},#{afterSale},#{stateValidTime},#{province},#{city},#{district})")
	public void insert(CollectOrder collectOrder);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public CollectOrder get(Long id);

	@Update("update `${table_name}` set `orderNumber`=#{orderNumber},`userId`=#{userId},`time`=#{time},`lastUpdateTime`=#{lastUpdateTime},`sum`=#{sum},`cash`=#{cash},`discount`=#{discount},`coupon`=#{coupon},`postage`=#{postage},`integral`=#{integral},`consumption`=#{consumption},`preferential`=#{preferential},`consignee`=#{consignee},`address`=#{address},`mobile`=#{mobile},`email`=#{email},`paymentMode`=#{paymentMode},`state`=#{state},`prestate`=#{prestate},`needInvoice`=#{needInvoice},`invoiceType`=#{invoiceType},`invoiceHead`=#{invoiceHead},`invoiceContent`=#{invoiceContent},`evaluation`=#{evaluation},`afterSale`=#{afterSale},`stateValidTime`=#{stateValidTime},`province`=#{province},`city`=#{city},`district`=#{district} where `id`=#{id}")
	public void update(CollectOrder collectOrder);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<CollectOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<CollectOrder> listAll();
}