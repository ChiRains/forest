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
//import com.qcloud.component.sellercenter.model.MerchantService;
//import com.qcloud.component.sellercenter.model.query.MerchantServiceQuery;
//
//public interface MerchantServiceMapper {
//
//	@Insert("insert into `sellercenter_merchant_service`(`id`,`merchantId`,`linkman`,`type`,`contactWay`,`desc`)"
//			+ " values(#{id},#{merchantId},#{linkman},#{type},#{contactWay},#{desc})")
//	public void insert(MerchantService merchantService);
//
//	@Select("select * from `sellercenter_merchant_service` where `id`=#{id}")
//	public MerchantService get(Long id);
//
//	@Update("update `sellercenter_merchant_service` set `merchantId`=#{merchantId},`linkman`=#{linkman},`type`=#{type},`contactWay`=#{contactWay},`desc`=#{desc} where `id`=#{id}")
//	public void update(MerchantService merchantService);
//
//	@Delete("delete from `sellercenter_merchant_service` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `sellercenter_merchant_service` limit #{start},#{count}")
//	public List<MerchantService> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `sellercenter_merchant_service`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `sellercenter_merchant_service`")
//	public List<MerchantService> listAll();
//}