package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.model.query.MerchantConsumptionDetailQuery;

public interface MerchantConsumptionDetailMapper {

	@Insert("insert into `sellercenter_merchant_consumption_detail`(`id`,`merchantId`,`point`,`remainder`,`time`,`desc`)"
			+ " values(#{id},#{merchantId},#{point},#{remainder},#{time},#{desc})")
	public void insert(MerchantConsumptionDetail merchantConsumptionDetail);

	@Select("select * from `sellercenter_merchant_consumption_detail` where `id`=#{id}")
	public MerchantConsumptionDetail get(Long id);

	@Update("update `sellercenter_merchant_consumption_detail` set `merchantId`=#{merchantId},`point`=#{point},`remainder`=#{remainder},`time`=#{time},`desc`=#{desc} where `id`=#{id}")
	public void update(MerchantConsumptionDetail merchantConsumptionDetail);

	@Delete("delete from `sellercenter_merchant_consumption_detail` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_merchant_consumption_detail` limit #{start},#{count}")
	public List<MerchantConsumptionDetail> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_merchant_consumption_detail`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_merchant_consumption_detail`")
	public List<MerchantConsumptionDetail> listAll();
}