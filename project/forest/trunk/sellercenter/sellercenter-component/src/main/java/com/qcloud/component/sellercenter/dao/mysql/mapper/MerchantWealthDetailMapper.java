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
//import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
//import com.qcloud.component.sellercenter.model.query.MerchantWealthDetailQuery;
//
//public interface MerchantWealthDetailMapper {
//
//	@Insert("insert into `sellercenter_merchant_wealth_detail`(`id`,`wealthId`,`merchantId`,`point`,`remainder`,`time`,`desc`,`type`)"
//			+ " values(#{id},#{wealthId},#{merchantId},#{point},#{remainder},#{time},#{desc},#{type})")
//	public void insert(MerchantWealthDetail merchantWealthDetail);
//
//	@Select("select * from `sellercenter_merchant_wealth_detail` where `id`=#{id}")
//	public MerchantWealthDetail get(Long id);
//
//	@Update("update `sellercenter_merchant_wealth_detail` set `wealthId`=#{wealthId},`merchantId`=#{merchantId},`point`=#{point},`remainder`=#{remainder},`time`=#{time},`desc`=#{desc},`type`=#{type} where `id`=#{id}")
//	public void update(MerchantWealthDetail merchantWealthDetail);
//
//	@Delete("delete from `sellercenter_merchant_wealth_detail` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `sellercenter_merchant_wealth_detail` limit #{start},#{count}")
//	public List<MerchantWealthDetail> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `sellercenter_merchant_wealth_detail`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `sellercenter_merchant_wealth_detail`")
//	public List<MerchantWealthDetail> listAll();
//}