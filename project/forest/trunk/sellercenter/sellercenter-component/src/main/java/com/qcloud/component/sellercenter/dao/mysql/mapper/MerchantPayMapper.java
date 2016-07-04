//package com.qcloud.component.sellercenter.dao.mysql.mapper;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import com.qcloud.component.sellercenter.model.MerchantPay;
//import com.qcloud.component.sellercenter.model.query.MerchantPayQuery;
//
//public interface MerchantPayMapper {
//
//	@Insert("insert into `sellercenter_merchant_pay`(`id`,`merchantId`,`type`,`account`)"
//			+ " values(#{id},#{merchantId},#{type},#{account})")
//	public void insert(MerchantPay merchantPay);
//
//	@Select("select * from `sellercenter_merchant_pay` where `id`=#{id}")
//	public MerchantPay get(Long id);
//
//	@Update("update `sellercenter_merchant_pay` set `merchantId`=#{merchantId},`type`=#{type},`account`=#{account} where `id`=#{id}")
//	public void update(MerchantPay merchantPay);
//
//	@Delete("delete from `sellercenter_merchant_pay` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `sellercenter_merchant_pay` limit #{start},#{count}")
//	public List<MerchantPay> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `sellercenter_merchant_pay`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `sellercenter_merchant_pay`")
//	public List<MerchantPay> listAll();
//}